package com.hzw.tourism.service.impl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.constant.RedisConstants;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.vo.HotelVo;
import com.hzw.tourism.entity.Hotel;
import com.hzw.tourism.entity.ScenicSpot;
import com.hzw.tourism.mapper.HotelMapper;
import com.hzw.tourism.qo.HotelQuery;
import com.hzw.tourism.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
@Slf4j
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private ScenicSpotServiceImpl scenicSpotService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Integer count0() {
        String key = RedisConstants.CACHE_HOTEL_COUNT0_KEY;
        String count = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count)) {
            Integer count1 = (Integer) JSON.parse(count);
            log.info("走缓存");
            return count1;
        } else {
            LambdaQueryWrapper<Hotel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.ne(Hotel::getState, 0);
            Integer count1 = hotelMapper.selectCount(queryWrapper);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count1), CommonConstants.TWO, TimeUnit.HOURS);
            log.info("存入缓存！！");
            return  count1;
        }
    }

    @Override
    public Integer count1() {
        String key = RedisConstants.CACHE_HOTEL_COUNT1_KEY;
        String count = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count)) {
            Integer count1 = (Integer) JSON.parse(count);
            log.info("走缓存");
            return count1;
        } else {
            LambdaQueryWrapper<Hotel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Hotel::getState, 1);
            Integer count1 = hotelMapper.selectCount(queryWrapper);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count), CommonConstants.FOUR, TimeUnit.HOURS);
            log.info("存入缓存！！");
            return  count1;
        }
    }

    @Override
    public void saveOrUpdateHotel(Hotel hotel) throws Exception{
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long id = hotel.getId();
        if (id == null) {
            hotel.setAddTime(date);
            hotel.setAddUserId(userId);
            hotelMapper.insert(hotel);
        } else {
            hotel.setModifyTime(date);
            hotel.setModifyUserId(userId);
            //1.更新数据库
            hotelMapper.updateById(hotel);
            //2.删除缓存
            redisTemplate.delete(RedisConstants.CACHE_HOTEL_KEY + id);
        }
    }

    @Override
    public Hotel findHotelById(Long id) throws Exception{
        String key = RedisConstants.CACHE_HOTEL_KEY + id;
        String hotelAll = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(hotelAll)) {
            Hotel hotel = JSON.parseObject(hotelAll, Hotel.class);
            log.info("走缓存");
            return  hotel;
        }
        //判断命中是否为空值 " "字符串
        if ("".equals(hotelAll)) {
            throw new Exception("这个酒店信息不存在");
        }
        Hotel hotel = hotelMapper.selectById(id);

        if (hotel == null) {
            redisTemplate.opsForValue().set(key, "", 1, TimeUnit.MINUTES);
            throw new Exception("这个酒店信息不存在");
        }
        redisTemplate.opsForValue().set(key, JSON.toJSONString(hotel), CommonConstants.ONE, TimeUnit.HOURS);
        log.info("存入缓存");
        return hotel;
    }


    @Override
    public void deleteById(Long id) {
        hotelMapper.deleteById(id);
        redisTemplate.delete(RedisConstants.CACHE_HOTEL_KEY + id);
        redisTemplate.delete(RedisConstants.CACHE_HOTEL_COUNT1_KEY);
        redisTemplate.delete(RedisConstants.CACHE_HOTEL_COUNT0_KEY);
    }

    @Override
    public Boolean updateStates(Hotel hotel) {
        Date date = new Date();
        hotel.setModifyTime(date);
        LambdaUpdateWrapper<Hotel> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper1.and(i -> i.eq(Hotel::getId, hotel.getId()).eq(Hotel::getDeleteStatus, 0))
                .set(Hotel::getState, hotel.getState());
        boolean isSuccess = update(lambdaQueryWrapper1);
        return isSuccess;
    }

    @Override
    public List<AnalysisVO> typeAll() {
        Integer hourRoom = lambdaQuery().eq(Hotel::getType, 0).eq(Hotel::getState, 1).count();
        Integer hotelRoom = lambdaQuery().eq(Hotel::getType, 1).eq(Hotel::getState, 1).count();
        Integer apartmentHouse = lambdaQuery().eq(Hotel::getType, 2).eq(Hotel::getState, 1).count();
        AnalysisVO analysisPlan = new AnalysisVO("民宿", hourRoom);
        AnalysisVO analysisTrain = new AnalysisVO("酒店", hotelRoom);
        AnalysisVO analysisHotel = new AnalysisVO("公寓", apartmentHouse);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisPlan);
        analysisVOS.add(analysisTrain);
        analysisVOS.add(analysisHotel);
        return analysisVOS;
    }

    @Override
    public List<AnalysisVO> stateAll() {
        Integer logout = lambdaQuery().eq(Hotel::getState, 0).eq(Hotel::getDeleteStatus, 1).count();
        Integer publish = lambdaQuery().eq(Hotel::getState, 1).count();
        Integer waitPublish = lambdaQuery().eq(Hotel::getState, 2).count();
        AnalysisVO analysisLogout = new AnalysisVO("倒闭", logout);
        AnalysisVO analysisPublish = new AnalysisVO("发布", publish);
        AnalysisVO analysisWaitPublish = new AnalysisVO("待发布", waitPublish);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisLogout);
        analysisVOS.add(analysisPublish);
        analysisVOS.add(analysisWaitPublish);
        return analysisVOS;
    }

    @Override
    public Page<Hotel> listPage(HotelQuery query) {
        //分页
        Page<Hotel> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Hotel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getHotelName()), Hotel::getHotelName, query.getHotelName());
        lambdaQueryWrapper.eq(query.getHotelStar()!=null, Hotel::getHotelStar, query.getHotelStar());
        lambdaQueryWrapper.le(query.getPrice()!=null,Hotel::getPrice, query.getPrice());
        lambdaQueryWrapper.eq(query.getScenicSpotId()!=null,Hotel::getScenicSpotId, query.getScenicSpotId());
        lambdaQueryWrapper.eq(query.getType()!=null,Hotel::getType, query.getType());
        lambdaQueryWrapper.eq(query.getState()!=null,Hotel::getState,query.getState());
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getAddress()),Hotel::getAddress,query.getAddress());
        return hotelMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public   List<Hotel> listHotelSpot(Long scenicSpotId) {

        //条件查询
        LambdaQueryWrapper<Hotel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ObjectUtils.isNotNull(scenicSpotId),Hotel::getScenicSpotId, scenicSpotId);
        List<Hotel> hotelList = list(lambdaQueryWrapper);
        return Optional.ofNullable(hotelList).orElse(Collections.emptyList());
    }

    @Override
    public List<ScenicSpot> listSpotHotel(Long scenicSpotId) {
        //条件查询
        LambdaQueryWrapper<ScenicSpot> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(scenicSpotId!=null,ScenicSpot::getId, scenicSpotId);
        List<ScenicSpot> scenicSpotList = scenicSpotService.list(lambdaQueryWrapper);
        return Optional.ofNullable(scenicSpotList).orElse(Collections.emptyList()); }

    @Override
    public List<HotelVo> getHotelSpot() {
        return hotelMapper.getHotelSpot();
    }


}
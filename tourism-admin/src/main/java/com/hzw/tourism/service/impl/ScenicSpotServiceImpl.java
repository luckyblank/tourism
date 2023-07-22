package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.vo.SpotVo;
import com.hzw.tourism.entity.ScenicSpot;
import com.hzw.tourism.mapper.ScenicSpotMapper;
import com.hzw.tourism.qo.ScenciSpotQuery;
import com.hzw.tourism.service.ScenicSpotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hzw.tourism.constant.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@Slf4j
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService {
@Autowired
private ScenicSpotMapper scenicSpotMapper;
@Autowired
private RedisTemplate<String,String> redisTemplate;
    @Override
    public Integer count0() {
        String key=CACHE__SCENIC_COUNT0_KEY;
        if (redisTemplate.hasKey(key)){
            String count0 = redisTemplate.opsForValue().get(key);
            Integer count = (Integer)JSON.parse(count0);
            log.info("走缓存...");
            return count;
        }else {
            Integer count = lambdaQuery().count();
            redisTemplate.opsForValue().set(key,JSON.toJSONString(count),4,TimeUnit.HOURS);
            log.info("存缓存...");
            return count;
        }    }

    @Override
    public Integer count1() {
        String key=CACHE_SCENIC__COUNT1_KEY;
        String count1 = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count1)){
            Integer count = (Integer)JSON.parse(count1);
            log.info("走缓存...");
            return count;
        }else {
            Integer count = lambdaQuery().eq(ScenicSpot::getDeleteStatus, 0).eq(ScenicSpot::getState,1).count();
            redisTemplate.opsForValue().set(key,JSON.toJSONString(count),CommonConstants.FOUR,TimeUnit.HOURS);
            log.info("存缓存...");
            return count;
        }    }

    @Override
    public Boolean saveOrUpdateScenicSpot(ScenicSpot scenicSpot) {
        Long userId = WebUtils.getUserId();
        String key= CACHE_SCENIC_KEY+scenicSpot.getId();
        Date date = new Date();
        Long id = scenicSpot.getId();
        if ( id== null) {
            scenicSpot.setAddTime(date);
            scenicSpot.setAddUserId(userId);
            boolean isSuccess = save(scenicSpot);
            return isSuccess;
        }else {
            scenicSpot.setModifyTime(date);
            scenicSpot.setModifyUserId(userId);
            //1.更新数据库数据
            boolean isSuccess = updateById(scenicSpot);
            //2.删缓存
            redisTemplate.delete(key);
            return isSuccess;

        }     }

    @Override
    public ScenicSpot findScenicSpotById(Long id) throws GlobalExceptionMyHandler {
        String key= CACHE_SCENIC_KEY+id;
        String scenicSpotById = redisTemplate.opsForValue().get(key);
        if (redisTemplate.hasKey(key)){
            ScenicSpot scenicSpot = JSON.parseObject(scenicSpotById, ScenicSpot.class);
            log.info("走缓存.....");
            return scenicSpot;
        }
        if ("".equals(scenicSpotById)){
           throw new GlobalExceptionMyHandler("该景点信息不存在");
        }

            ScenicSpot scenicSpot = scenicSpotMapper.selectById(id);
            if (ObjectUtils.isEmpty(scenicSpot)){
                //把null存入到缓存
                redisTemplate.opsForValue().set(key,"",CommonConstants.TWO,TimeUnit.MINUTES);
                throw new GlobalExceptionMyHandler("该景点信息不存在");
            }
            redisTemplate.opsForValue().set(key,JSON.toJSONString(scenicSpot),CommonConstants.TWO,TimeUnit.HOURS);
            log.info("存入缓存.....");
            return scenicSpot;


    }

    @Override
    public Boolean deleteById(Long id) {
        String scenicKey= CACHE_SCENIC_KEY+id;
        String cacheScenicCount1Key=CACHE_SCENIC__COUNT1_KEY;
        String cacheScenicCount0Key=CACHE__SCENIC_COUNT0_KEY;
        Boolean isSuccess = deleteById(id);
        redisTemplate.delete(scenicKey);
        redisTemplate.delete(cacheScenicCount0Key);
        redisTemplate.delete(cacheScenicCount1Key);
        return isSuccess;
    }

    @Override
    public Boolean updateStates(ScenicSpot scenicSpot) {
        String cacheScenicCount1Key=CACHE_SCENIC__COUNT1_KEY;
        String cacheScenicCount0Key=CACHE__SCENIC_COUNT0_KEY;
        Date date = new Date();
        scenicSpot.setModifyTime(date);
        LambdaUpdateWrapper<ScenicSpot> scenicSpotLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        scenicSpotLambdaUpdateWrapper.and(i->i.eq(ScenicSpot::getId,scenicSpot.getId()).eq(ScenicSpot::getDeleteStatus,0))
                .set(ScenicSpot::getState,scenicSpot.getState());
        boolean isSuccess = update(scenicSpotLambdaUpdateWrapper);
        redisTemplate.delete(cacheScenicCount0Key);
        redisTemplate.delete(cacheScenicCount1Key);
      return isSuccess;
    }

    @Override
    public List<AnalysisVO> stateAll() {
        Integer logout = lambdaQuery().eq(ScenicSpot::getState, 0).eq(ScenicSpot::getDeleteStatus, 1).count();
        Integer publish = lambdaQuery().eq(ScenicSpot::getState, 1).count();
        Integer waitPublish = lambdaQuery().eq(ScenicSpot::getState, 2).count();
        AnalysisVO analysisLogout= new AnalysisVO("注销", logout);
        AnalysisVO analysisPublish = new AnalysisVO("发布", publish);
        AnalysisVO analysisWaitPublish= new AnalysisVO("待发布", waitPublish);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisLogout);
        analysisVOS.add(analysisPublish);
        analysisVOS.add(analysisWaitPublish);
        return analysisVOS;  }

    @Override
    public List<ScenicSpot> findAll() {
        String scenicSpotAll = redisTemplate.opsForValue().get("scenicSpot:findAll");
        if (StringUtils.isNotBlank(scenicSpotAll)){
            List<ScenicSpot> scenicSpots = JSON.parseArray(scenicSpotAll, ScenicSpot.class);
            log.info("走缓存....");
            return scenicSpots;
        }else {
            List<ScenicSpot> list =lambdaQuery().eq(ScenicSpot::getDeleteStatus, 0).eq(ScenicSpot::getState, 1).list();
            redisTemplate.opsForValue().set("scenicSpot:findAll",JSON.toJSONString(list), CommonConstants.ONE,TimeUnit.HOURS);
            return Optional.ofNullable(list).orElse(Collections.emptyList());
        }    }

    @Override
    public IPage<ScenicSpot>  listPage(ScenciSpotQuery query) {
        //分页
        Page<ScenicSpot> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<ScenicSpot> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getSpotName()),ScenicSpot::getSpotName,query.getSpotName());
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getSpotAddress()),ScenicSpot::getSpotAddress,query.getSpotAddress());
        lambdaQueryWrapper.eq(query.getSpotStar()!=null,ScenicSpot::getSpotStar, query.getSpotStar());
        lambdaQueryWrapper.eq(query.getState()!=null,ScenicSpot::getState,query.getState());
        lambdaQueryWrapper.eq(query.getScenicSpotId()!=null,ScenicSpot::getId,query.getScenicSpotId());
        if (ObjectUtils.isNotEmpty(query.getLowPrice()) || ObjectUtils.isNotEmpty(query.getHighPrice())){
            lambdaQueryWrapper.between(ScenicSpot::getTicketsMessage,query.getLowPrice(),query.getHighPrice());
        }
        //分页
        IPage<ScenicSpot> result= scenicSpotMapper.selectPage(page,lambdaQueryWrapper);
        return result;
    }

    @Override
    public List<SpotVo> getSpotHotel() {
        List<SpotVo> spotHotel = scenicSpotMapper.getSpotHotel();
        return Optional.ofNullable(spotHotel).orElse(Collections.emptyList());
    }
}

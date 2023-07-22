package com.hzw.tourism.service.impl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.entity.Routel;
import com.hzw.tourism.mapper.RoutelMapper;
import com.hzw.tourism.qo.RouteQuery;
import com.hzw.tourism.service.AdminService;
import com.hzw.tourism.service.RoutelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class RoutelServiceImpl extends ServiceImpl<RoutelMapper, Routel> implements RoutelService {

    @Autowired
    private RoutelMapper routelMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public Integer count0() {
        String key=CACHE__ROUTE_COUNT0_KEY;
        if (redisTemplate.hasKey(key)){
            String travelRouteCount0 = redisTemplate.opsForValue().get(key);
            Integer count0 =(Integer) JSON.parse(travelRouteCount0);
            log.info("走缓存.......");
            return count0;
        }else {
            Integer count = lambdaQuery().count();
            log.info("存入缓存......");
            redisTemplate.opsForValue().set(key,JSON.toJSONString(count), CommonConstants.FOUR,TimeUnit.HOURS);
            return count;
        }    }

    @Override
    public Integer count1() {
        String key=CACHE_ROUTE_COUNT1_KEY;
        if (redisTemplate.hasKey(key)){
            String travelRouteCount1 = redisTemplate.opsForValue().get(key);
            Integer count0 =(Integer) JSON.parse(travelRouteCount1);
            log.info("走缓存.......");
            return count0;
        }else {
            Integer count = lambdaQuery().eq(Routel::getState,1).count();
            redisTemplate.opsForValue().set(key,JSON.toJSONString(count),CommonConstants.FOUR,TimeUnit.HOURS);
            return count;

        }    }

    @Override
    public Boolean saveOrUpdateRoutel(Routel routel) {
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long id = routel.getId();
        String key=CACHE_ROUTE_KEY+id;
        if ( id== null ) {
            String orderCode = UUID.randomUUID().toString();
            routel.setProductCode(orderCode);
            routel.setAddTime(date);
            routel.setAddUserId(userId);
            boolean insert = save(routel);
            return insert;
        }
            routel.setModifyTime(date);
            routel.setModifyUserId(userId);
            boolean isSuccess = updateById(routel);
        //删除缓存
            redisTemplate.delete(key);
            return isSuccess;

    }

    @Override
    public Routel findRoutelById(Long id) throws GlobalExceptionMyHandler{
        String key=CACHE_ROUTE_KEY+id;
        String routeById = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(routeById)){
            Routel travelRoute = JSON.parseObject(routeById, Routel.class);
            log.info("走缓存.....");
            return travelRoute;
        }
        if ("".equals(routeById)){
           throw new GlobalExceptionMyHandler("该路线不存在");
        }

            Routel travelRoute = routelMapper.selectById(id);
            if (ObjectUtils.isEmpty(travelRoute)){
                redisTemplate.opsForValue().set(key,"",2,TimeUnit.SECONDS);
                throw new GlobalExceptionMyHandler("该路线不存在");
            }
            redisTemplate.opsForValue().set(key,JSON.toJSONString(travelRoute),CommonConstants.TWO,TimeUnit.HOURS);
            log.info("存入缓存....");
            return  travelRoute;
        }

    @Override
    public Boolean deleteById(Long id) {
        String routeCount0Key=CACHE__ROUTE_COUNT0_KEY;
        String routeCount1Key=CACHE_ROUTE_COUNT1_KEY;
        String routeKey=CACHE_ROUTE_KEY;
        Boolean isSuccess = deleteById(id);
        redisTemplate.delete(routeCount0Key);
        redisTemplate.delete(routeCount1Key);
        redisTemplate.delete(routeKey);
        return  isSuccess;
    }

    @Override
    public Boolean updateStates(Routel routel) {
        Date date = new Date();
        routel.setModifyTime(date);
        LambdaUpdateWrapper<Routel> routelLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        routelLambdaUpdateWrapper.and(i->i.eq(Routel::getId,routel.getId()).eq(Routel::getDeleteStatus,0))
                .set(Routel::getState,routel.getState());
        boolean isSuccess = update(routelLambdaUpdateWrapper);
        String routeCount0Key=CACHE__ROUTE_COUNT0_KEY;
        String routeCount1Key=CACHE_ROUTE_COUNT1_KEY;
        redisTemplate.delete(routeCount0Key);
        redisTemplate.delete(routeCount1Key);
        return isSuccess;
    }

    @Override
    public List<AnalysisVO> stateAll() {
        Integer publish = lambdaQuery().eq(Routel::getState, 1).count();
        Integer waitPublish = lambdaQuery().eq(Routel::getState, 2).count();
        AnalysisVO analysisPublish = new AnalysisVO("发布", publish);
        AnalysisVO analysisWaitPublish = new AnalysisVO("待发布", waitPublish);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisPublish);
        analysisVOS.add(analysisWaitPublish);
        return analysisVOS;
    }


    @Override
    public Page<Routel> listPage(RouteQuery query) {

        //分页
        Page<Routel> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Routel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getStartSite()),Routel::getStartSite,query.getStartSite());
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getEndSite()),Routel::getEndSite,query.getEndSite());
        lambdaQueryWrapper.le(query.getPrice()!=null,Routel::getPrice,query.getPrice());
        lambdaQueryWrapper.eq(query.getState()!=null,Routel::getState,query.getState());

        Page<Routel> result = routelMapper.selectPage(page, lambdaQueryWrapper);
        return result;




}
}

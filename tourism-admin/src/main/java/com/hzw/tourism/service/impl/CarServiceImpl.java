package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.Car;
import com.hzw.tourism.mapper.CarMapper;
import com.hzw.tourism.qo.CarQuery;
import com.hzw.tourism.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.constant.RedisConstants;
import com.hzw.tourism.vo.AnalysisVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
@Slf4j
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Integer count1() {
        String key = RedisConstants.CACHE_CAR_COUNT0_KEY;
        String count = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count)) {
            Integer count1 = (Integer) JSON.parse(count);
            log.info("走缓存");
            return count1;
        } else {
            LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Car::getState, 1);
            Integer count1 = carMapper.selectCount(queryWrapper);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count1), CommonConstants.FOUR, TimeUnit.HOURS);
            log.info("存入缓存！！");
            return  count1;
        }
    }


    @Override
    @Transactional
    public ResponseResult saveOrUpdateCar(Car car) {
        Date date = new Date();
        if (car == null) {
            return ResponseResult.fail();
        }
        Long id = car.getId();
        if (id == null) {
            car.setAddTime(date);
            return save(car) ? ResponseResult.success() : ResponseResult.fail();
        } else {
            car.setModifyTime(date);
            //1.更新数据库
            //2.删除缓存
            boolean update = updateById(car);
            if (!update) {
                return ResponseResult.fail();
            }
            redisTemplate.delete(RedisConstants.CACHE_CAR_KEY + id);
            return ResponseResult.success(update);
        }
    }

    @Override
    public ResponseResult findCarById(Long id) {
        String key = RedisConstants.CACHE_CAR_KEY + id;
        //1.从redis查询缓存
        String carAll = redisTemplate.opsForValue().get(key);
        //2.判断是否存在
        if (StringUtils.isNotBlank(carAll)) {
            //3.存在，直接放回
            Car car = JSON.parseObject(carAll, Car.class);
            log.info("走缓存");
            return new ResponseResult(CommonConstants.SUCCESS, "通过id查询出行成功", car);
        }
        //4.判断命中是否为空值 " "字符串
        if ("".equals(carAll)) {
            return new ResponseResult(CommonConstants.FAIL, "这个出行信息不存在！！！");
        }
        //5.不存在 查数据库
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Car::getId, id);
        Car car = getOne(queryWrapper);
        //5.不存在 返回错误
        if (car == null) {
            //解决缓存穿透问题--》恶意查找不存在数据
            redisTemplate.opsForValue().set(key, "", CommonConstants.TWO, TimeUnit.MINUTES);
            return new ResponseResult(CommonConstants.FAIL, "这个出行不存在！！！");
        }
        //6.存在存入redis
        redisTemplate.opsForValue().set(key, JSON.toJSONString(car), CommonConstants.ONE, TimeUnit.HOURS);
        log.info("存入缓存");
        return new ResponseResult(CommonConstants.SUCCESS, "通过id查询用户成功", car);
    }


    @Override
    public void deleteById(Long id) throws Exception{
        String key = RedisConstants.CACHE_CAR_KEY + id;
        carMapper.deleteById(id);
        redisTemplate.delete(key);
        redisTemplate.delete(RedisConstants.CACHE_CAR_COUNT0_KEY);
    }

    @Override
    public Boolean updateStates(Car car) throws Exception {
        Date date = new Date();
        car.setModifyTime(date);
        return carMapper.updateState(car);
    }

    @Override
    public List<AnalysisVO> typeAll() throws Exception {
        Integer plane = lambdaQuery().eq(Car::getType, 0).eq(Car::getState, 1).count();
        Integer train = lambdaQuery().eq(Car::getType, 1).eq(Car::getState, 1).count();
        AnalysisVO analysisPlan = new AnalysisVO("高铁", plane);
        AnalysisVO analysisTrain = new AnalysisVO("火车", train);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisPlan);
        analysisVOS.add(analysisTrain);
        return analysisVOS;
    }

    @Override
    public List<AnalysisVO> stateAll() {
        Integer logout = lambdaQuery().eq(Car::getState, 0).eq(Car::getDeleteStatus, 1).count();
        Integer publish = lambdaQuery().eq(Car::getState, 1).count();
        Integer waitPublish = lambdaQuery().eq(Car::getState, 2).count();
        AnalysisVO analysisLogout = new AnalysisVO("注销", logout);
        AnalysisVO analysisPublish = new AnalysisVO("发布", publish);
        AnalysisVO analysisWaitPublish = new AnalysisVO("待发布", waitPublish);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisLogout);
        analysisVOS.add(analysisPublish);
        analysisVOS.add(analysisWaitPublish);
        return analysisVOS;

    }


    @Override
    public  Page<Car> listPage(CarQuery query) {
        //分页
        Page<Car> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Car> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getStartPlace()), Car::getStartPlace, query.getStartPlace());
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getEndPlace()), Car::getEndPlace, query.getEndPlace());
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getIntermediateStop()), Car::getIntermediateStop, query.getIntermediateStop());
        lambdaQueryWrapper.eq(query.getType() != null, Car::getType, query.getType());
        lambdaQueryWrapper.eq(query.getState() != null, Car::getState, query.getState());
        //lambdaQueryWrapper.eq(Car::getState,1);
        Page<Car> result = carMapper.selectPage(page, lambdaQueryWrapper);
        return result;

    }


}

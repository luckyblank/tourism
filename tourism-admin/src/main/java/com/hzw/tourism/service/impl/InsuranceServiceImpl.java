package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.entity.Insurance;
import com.hzw.tourism.mapper.InsuranceMapper;
import com.hzw.tourism.qo.InsuranceQuery;
import com.hzw.tourism.service.InsuranceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hzw.tourism.constant.RedisConstants.CACHE_INSURANCE_COUNT0_KEY;
import static com.hzw.tourism.constant.RedisConstants.CACHE_INSURANCE_COUNT1_KEY;
import static com.hzw.tourism.constant.RedisConstants.CACHE_INSURANCE_KEY;

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
public class InsuranceServiceImpl extends ServiceImpl<InsuranceMapper, Insurance> implements InsuranceService {

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Integer count0() {
        String key=CACHE_INSURANCE_COUNT0_KEY;
        String count = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count)) {
            Integer count2 = (Integer) JSON.parse(count);
            log.info("走缓存...");
            return count2;
        } else {
            Integer count1 = lambdaQuery().eq(Insurance::getState, 1).count();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count1), CommonConstants.TWO, TimeUnit.HOURS);
            log.info("存入缓存");
            return  count1;
        }    }

    @Override
    public Integer count1() {
        String key=CACHE_INSURANCE_COUNT1_KEY;
        String count = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(count)) {
            Integer count2 = (Integer) JSON.parse(count);
            log.info("走缓存...");
            return  count2;
        } else {
            Integer count1 = lambdaQuery().eq(Insurance::getDeleteStatus, 1).eq(Insurance::getState,0).count();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count1), CommonConstants.ONE, TimeUnit.HOURS);
            log.info("存入缓存");
            return count1;
        }      }

    @Override
    public Boolean saveOrUpdateInsurance(Insurance insurance) {
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long id = insurance.getId();
        if (id == null) {
            insurance.setAddTime(date);
            insurance.setAddUserId(userId);
            return save(insurance);
        }else {
            //1.更改数据库
            insurance.setModifyTime(date);
            insurance.setModifyUserId(userId);
            boolean update1 = update(insurance, null);
            //2.删除缓存
            redisTemplate.delete(CACHE_INSURANCE_KEY+id);
            return update1;
        }
    }

    @Override
    public Insurance findInsuranceById(Long id) throws GlobalExceptionMyHandler {
        String key=CACHE_INSURANCE_KEY+id;
        String findInsurance = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(findInsurance)){
            Insurance insurance = JSON.parseObject(findInsurance, Insurance.class);
            log.info("走缓存....");
            return insurance;
        }
            if ("".equals(findInsurance)){
              throw new GlobalExceptionMyHandler("这个保险不存在");
            }
        Insurance insurance = insuranceMapper.selectById(id);
            if (insurance==null){
                //查询不存在 缓存
                redisTemplate.opsForValue().setIfAbsent(key,"",1,TimeUnit.MINUTES);
                throw new GlobalExceptionMyHandler("这个保险不存在");
            }
            redisTemplate.opsForValue().set("insurance:findInsuranceById:"+id,JSON.toJSONString(insurance),4,TimeUnit.HOURS);
            return insurance;
        }

    @Override
    public Boolean deleteById(Long id) {
        return deleteById(id);
    }

    @Override
    public Boolean updateStates(Insurance insurance) {
        Date date = new Date();
        insurance.setModifyTime(date);
        LambdaUpdateWrapper<Insurance> hotelLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        hotelLambdaUpdateWrapper.and(i->i.eq(Insurance::getId,insurance.getId()).eq(Insurance::getDeleteStatus,0))
                .set(Insurance::getState,insurance.getState());
        return update(hotelLambdaUpdateWrapper);
    }

    @Override
    public List<AnalysisVO> InsuranceCompanyAll() {
        Integer lifeInsurance = lambdaQuery().eq(Insurance::getInsuranceCompany, 0).eq(Insurance::getState, 1).count();
        Integer healthyAndSafe = lambdaQuery().eq(Insurance::getInsuranceCompany, 1).eq(Insurance::getState,1).count();
        Integer floatingInsuranceEveryDay = lambdaQuery().eq(Insurance::getInsuranceCompany, 2).eq(Insurance::getState,1).count();
        Integer aLifeWithoutWorry = lambdaQuery().eq(Insurance::getInsuranceCompany, 3).eq(Insurance::getState,1).count();
        AnalysisVO analysisPlan = new AnalysisVO("人寿保险", lifeInsurance);
        AnalysisVO analysisTrain = new AnalysisVO("健康平安", healthyAndSafe);
        AnalysisVO analysisCar = new AnalysisVO("天天飘保险", floatingInsuranceEveryDay);
        AnalysisVO analysisALife = new AnalysisVO("一生无忧", aLifeWithoutWorry);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisPlan);
        analysisVOS.add(analysisTrain);
        analysisVOS.add(analysisCar);
        analysisVOS.add(analysisALife);
        return analysisVOS;
    }

    @Override
    public List<AnalysisVO> typeAll() {
        Integer wholeBodyInsurance = lambdaQuery().eq(Insurance::getType, 0).eq(Insurance::getState, 1).count();
        Integer hotelInsurance = lambdaQuery().eq(Insurance::getType, 1).eq(Insurance::getState,1).count();
        Integer scenicSpotInsurance = lambdaQuery().eq(Insurance::getType, 2).eq(Insurance::getState,1).count();
        Integer travelInsurance = lambdaQuery().eq(Insurance::getType, 3).eq(Insurance::getState,1).count();
        Integer routeInsurance = lambdaQuery().eq(Insurance::getType, 4).eq(Insurance::getState,1).count();
        AnalysisVO analysisPlan = new AnalysisVO("全身险", wholeBodyInsurance);
        AnalysisVO analysisTrain = new AnalysisVO("酒店保险", hotelInsurance);
        AnalysisVO analysisCar = new AnalysisVO("景区保险", scenicSpotInsurance);
        AnalysisVO analysisALife = new AnalysisVO("出行保险", travelInsurance);
        AnalysisVO analysisRoute = new AnalysisVO("路线保险", routeInsurance);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisPlan);
        analysisVOS.add(analysisTrain);
        analysisVOS.add(analysisCar);
        analysisVOS.add(analysisALife);
        analysisVOS.add(analysisRoute);
        return analysisVOS;
    }



    @Override
    public List<AnalysisVO> stateAll() {
        Integer logout = lambdaQuery().eq(Insurance::getState, 0).eq(Insurance::getDeleteStatus, 1).count();
        Integer publish = lambdaQuery().eq(Insurance::getState, 1).count();
        Integer waitPublish = lambdaQuery().eq(Insurance::getState, 2).count();
        AnalysisVO analysisLogout= new AnalysisVO("注销", logout);
        AnalysisVO analysisPublish = new AnalysisVO("发布", publish);
        AnalysisVO analysisWaitPublish= new AnalysisVO("待发布", waitPublish);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisLogout);
        analysisVOS.add(analysisPublish);
        analysisVOS.add(analysisWaitPublish);
        return analysisVOS;
    }

    @Override
    public List<Insurance> findAll() {
        String insuranceAll = redisTemplate.opsForValue().get("insurance:findAll");
        if (StringUtils.isNotBlank(insuranceAll)){
            List<Insurance> insurances = JSON.parseArray(insuranceAll, Insurance.class);
            log.info("走缓存......");
            return Optional.ofNullable(insurances).orElse(Collections.emptyList());
        }else {
            List<Insurance> insurancelist = lambdaQuery().eq(Insurance::getState, 1).list();
            redisTemplate.opsForValue().set("insurance:findAll",JSON.toJSONString(insurancelist),2,TimeUnit.HOURS);
            return Optional.ofNullable(insurancelist).orElse(Collections.emptyList());
        }    }

    @Override
    public IPage<Insurance> listPage(InsuranceQuery query) {
        //分页
        Page<Insurance> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Insurance> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getTitle()),Insurance::getTitle,query.getTitle());
        lambdaQueryWrapper.le(query.getPrice()!=null,Insurance::getPrice,query.getPrice());
        lambdaQueryWrapper.eq(query.getType()!=null,Insurance::getType,query.getType());
        lambdaQueryWrapper.eq(query.getState()!=null,Insurance::getState,query.getState());
        //分页
       IPage<Insurance> result = insuranceMapper.selectPage(page, lambdaQueryWrapper);
       return result;
    }
}

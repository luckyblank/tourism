package com.hzw.tourism.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.InsuranceUserInfo;
import com.hzw.tourism.service.InsuranceUserInfoService;
import com.hzw.tourism.service.OrderService;
import com.hzw.tourism.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-02-04
 */
@RestController
@RequestMapping("/insuranceUserInfo")
public class TInsuranceUserInfoController {
 @Autowired
 private OrderService orderService;

    @Autowired
    private InsuranceUserInfoService insuranceUserInfoService;

    /**
     *
     *根据用户id获取保险
     * @param id
     * @return
     */
    @GetMapping("/getUserInfoById")
    public ResponseResult getUserInfoById(Long id){
        if (id==null){
            return ResponseResult.fail();
        }
        InsuranceUserInfo userInfo = insuranceUserInfoService.getById(id);
        return ResponseResult.success(userInfo);
    }
    @GetMapping("/deleteById")
    public ResponseResult deleteById(Long id){
        if (id==null){
            return ResponseResult.fail();
        }

        return insuranceUserInfoService.removeById(id)?ResponseResult.success():ResponseResult.fail();
    }

    /**
     * 获取用户保险列表
     * @return
     */
    @GetMapping("/getUserInfoList")
    public ResponseResult getUserInfoList(){
        Long userId = WebUtils.getUserId();
        LambdaQueryWrapper<InsuranceUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InsuranceUserInfo::getUserId,userId);
        List<InsuranceUserInfo> list = insuranceUserInfoService.list(queryWrapper);
        if (list==null){
            return ResponseResult.fail();
        }
        return ResponseResult.success(list);
    }









}

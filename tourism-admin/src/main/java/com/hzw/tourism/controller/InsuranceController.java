package com.hzw.tourism.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Insurance;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.qo.InsuranceQuery;
import com.hzw.tourism.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@RestController
@RequestMapping("insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    /**
     * 查看发布保险数量
     * @return
     */

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('system:insurance:count')")
    public ResponseResult count0() throws GlobalExceptionHandler {
        return ResponseResult.success(insuranceService.count0());
    }
    @GetMapping("/count1")
    @PreAuthorize("hasAuthority('system:insurance:count1')")
    public ResponseResult count1() throws GlobalExceptionHandler{
        return ResponseResult.success(insuranceService.count1());
    }


    /**
     * 新增或修改保险方式
     * @param insurance
     * @return
     */
    @PostMapping("/saveOrUpdateInsurance")
    @PreAuthorize("hasAuthority('system:insurance:saveOrUpdateInsurance')")
    public ResponseResult saveOrUpdateInsurance(@RequestBody Insurance insurance) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(insurance)){
            return ResponseResult.fail();
        }
        Boolean saveOrUpdateInsurance = insuranceService.saveOrUpdateInsurance(insurance);
        boolean notEmpty = ObjectUtils.isNotEmpty(insurance.getId());
        return ResponseResult.isSuccess(saveOrUpdateInsurance,notEmpty?"修改":"新增");
    }
    /**
     * 工具id删除保险信息
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:insurance:deleteById')")
    public ResponseResult deleteById(Long id) throws GlobalExceptionHandler{
        Assert.notNull(id,"id不能为空");
        return ResponseResult.isSuccess(insuranceService.deleteById(id),"删除" );
    }

    /**
     * 列表条件分页查询保险
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody InsuranceQuery query) throws GlobalExceptionHandler {
        IPage<Insurance> result = insuranceService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());
    }


    /**
     * 通过id查询保险方式
     * @param id
     * @return
     */
    @GetMapping("/findInsuranceById")
    public ResponseResult findInsuranceById(@RequestParam Long id) throws GlobalExceptionHandler{
        Assert.notNull(id,"id不能为空");
        return ResponseResult.success(insuranceService.findInsuranceById(id));

    }



    /**
     * 根据id删除保险信息
     * @param insurance
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:insurance:updateStates')")
    public ResponseResult updateStates(@RequestBody Insurance insurance) throws GlobalExceptionHandler{
        return ResponseResult.isSuccess(insuranceService.updateStates(insurance),"删除");
    }

    /**
     * 查找保险所有类型
     * @return
     */
    @GetMapping("/typeAll")
    @PreAuthorize("hasAuthority('system:insurance:typeAll')")
    public ResponseResult typeAll() throws GlobalExceptionHandler {
        return ResponseResult.success(insuranceService.typeAll());

    }

    /**
     * 查找酒店所有状态数量
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:insurance:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler {
        return ResponseResult.success(insuranceService.stateAll());

    }


    /**
     * 查找所有存在的保险
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll() throws GlobalExceptionHandler{
    return ResponseResult.success(insuranceService.findAll());
    }

    @GetMapping("/InsuranceCompanyAll")
    public ResponseResult InsuranceCompanyAll() throws GlobalExceptionHandler{
        return ResponseResult.success(insuranceService.InsuranceCompanyAll());
    }
}

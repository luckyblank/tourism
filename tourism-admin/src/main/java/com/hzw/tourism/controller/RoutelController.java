package com.hzw.tourism.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Routel;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.qo.RouteQuery;
import com.hzw.tourism.service.RoutelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
@RequestMapping("/travelRoute")
public class RoutelController {
    private final static Logger logger= LoggerFactory.getLogger(RoutelController.class);
    @Autowired
    private RoutelService routelService;



    /**
     * 查看发布路线数量
     * @return
     */

    @GetMapping("/count0")
    @PreAuthorize("hasAuthority('system:routel:count')")
    public ResponseResult count0(){
        return ResponseResult.success(routelService.count0());
    }

    /**
     * 查看发布状态数量
     * @return
     */
    @GetMapping("/count1")
    @PreAuthorize("hasAuthority('system:routel:count1')")
    public ResponseResult count1(){
        return ResponseResult.success(routelService.count1());
    }


    /**
     * 新增或修改路线方式
     */
    @PostMapping("/saveOrUpdateRoutel")
    @PreAuthorize("hasAuthority('system:routel:saveOrUpdateRoutel')")
    public ResponseResult saveOrUpdateRoutel(@RequestBody Routel routel) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(routel)){
            return ResponseResult.fail("route不能为null");
        }
        Boolean aBoolean = routelService.saveOrUpdateRoutel(routel);
        return ResponseResult.isSuccess(aBoolean, ObjectUtils.isEmpty(routel.getId())?"新增":"修改");

    }
    /**
     * 工具id删除路线信息
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:routel:deleteById')")
    public ResponseResult deleteById(Long id) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(id)){
            return ResponseResult.fail("id不能为null");
        }
        return ResponseResult.isSuccess( routelService.deleteById(id),"删除");
    }
    /**
     * 列表条件分页查询路线
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody RouteQuery query) throws GlobalExceptionHandler{
        Page<Routel> result = routelService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());

    }

    /**
     * 通过id查询路线方式
     * @param id
     * @return
     */
    @GetMapping("/findById")
    //@PreAuthorize("hasAuthority('system:routel:findRoutelById')")
    public ResponseResult findRoutelById(@RequestParam Long id) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(id)){
            return ResponseResult.fail("id不能为null");
        }
        return ResponseResult.success(routelService.findRoutelById(id));

    }


    /**
     * 根据id删除路线状态
     * @param routel
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:routel:updateStates')")
    public ResponseResult updateStates(@RequestBody Routel routel) throws GlobalExceptionHandler {
        Boolean isSuccess = routelService.updateStates(routel);
        return ResponseResult.isSuccess(isSuccess,"删除路线");

    }


    /**
     * 查找所有状态数量
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:routel:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler{
        return ResponseResult.success(routelService.stateAll());

    }

}

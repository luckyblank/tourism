package com.hzw.tourism.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.vo.SpotVo;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.ScenicSpot;
import com.hzw.tourism.qo.ScenciSpotQuery;
import com.hzw.tourism.service.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/scenic")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;


    /**
     * 查看发布攻略数量
     * @return
     */

    @GetMapping("/count0")
    @PreAuthorize("hasAuthority('system:scenicSpot:count')")
    public ResponseResult count0(){
        return ResponseResult.success( scenicSpotService.count0());
    }

    /**
     * 查看发布状态数量
     * @return
     */
    @GetMapping("/count1")
    @PreAuthorize("hasAuthority('system:scenicSpot:count1')")
    public ResponseResult count1(){
        return ResponseResult.success(scenicSpotService.count1());
    }


    /**
     * 新增或修改攻略方式
     * @param scenicSpot
     * @return
     */
    @PostMapping("/saveOrUpdateScenicSpot")
    @PreAuthorize("hasAuthority('system:scenicSpot:saveOrUpdateScenicSpot')")
    public ResponseResult saveOrUpdateScenicSpot(@RequestBody ScenicSpot scenicSpot) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(scenicSpot)){
            return ResponseResult.fail("scenicSpot不能为null");
        }
        Boolean isSuccess = scenicSpotService.saveOrUpdateScenicSpot(scenicSpot);
        return ResponseResult.isSuccess(isSuccess, ObjectUtils.isEmpty(scenicSpot.getId())?"新增":"修改");


    }
    /**
     * 工具id删除攻略信息
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:scenicSpot:deleteById')")
    public ResponseResult deleteById(Long id) throws GlobalExceptionHandler{
        return ResponseResult.isSuccess(scenicSpotService.deleteById(id),"删除");
    }

    /**
     * 列表条件分页查询攻略
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody ScenciSpotQuery query) throws GlobalExceptionHandler {
        IPage<ScenicSpot> result = scenicSpotService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
        return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());

    }
    /**
     * 通过id查询攻略方式
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public ResponseResult findScenicSpotById(@RequestParam Long id) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(id)){
            return ResponseResult.fail("id不能为null");
        }
        ScenicSpot spot = scenicSpotService.findScenicSpotById(id);
        return ResponseResult.success(spot);

    }



    /**
     * 根据id删除攻略信息
     * @param scenicSpot
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:scenicSpot:updateStates')")
    public ResponseResult updateStates(@RequestBody ScenicSpot scenicSpot) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(scenicSpot)){
            return ResponseResult.fail("scenicSpot不能为null");
        }
        return ResponseResult.isSuccess(scenicSpotService.updateStates(scenicSpot),"修改状态");
    }


    /**
     * 查找所有状态数量
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:scenicSpot:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler {
        return ResponseResult.success(scenicSpotService.stateAll());

    }


    /**
     * 查找所有存在的攻略
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll() throws GlobalExceptionHandler {
        return ResponseResult.success(scenicSpotService.findAll());

    }


    @GetMapping("/getSpotHotel")
    public List<SpotVo> getSpotHotel() throws GlobalExceptionHandler {
        return scenicSpotService.getSpotHotel();
    }

}

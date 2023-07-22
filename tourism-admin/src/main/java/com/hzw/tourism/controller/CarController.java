package com.hzw.tourism.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Car;
import com.hzw.tourism.qo.CarQuery;
import com.hzw.tourism.service.CarService;
import com.hzw.tourism.vo.AnalysisVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
@Api(tags = "security-测试接口")
@RestController
@RequestMapping("/car")
public class CarController {
    private final static Logger log= LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    /**
     * 查看发布出行数量
     * @return
     */
    @GetMapping("/count")
    @PreAuthorize("hasAuthority('system:car:count')")
    public ResponseResult count1(){
        try {
            Integer count1 = carService.count1();
            return ResponseResult.success(count1);
        } catch (Exception e) {
           log.error("获取所有出行数量失败={}",e.getMessage());
           return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 新增或修改出行方式
     * @param car
     * @return
     */
    @PostMapping("/saveOrUpdateCar")
    @PreAuthorize("hasAuthority('system:car:saveOrUpdateCar')")
    public ResponseResult saveOrUpdateCar(@RequestBody Car car) {
        return carService.saveOrUpdateCar(car);
    }
    /**
     * 通过id查询出行方式
     * @param id
     * @return
     */
    @GetMapping("/findCarById")
    public ResponseResult findCarById(@RequestParam Long id){
        return carService.findCarById(id);

    }

    /**
     * 根据id删除出行信息
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:car:deleteById')")
    public ResponseResult deleteById(Long id){
        try {
            Assert.notNull(id,"id不能为空");
            carService.deleteById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error("删除出行失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 根据id更新出行状态
     * @param car
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:car:updateStates')")
    public ResponseResult updateStates(@RequestBody Car car) {
        if (ObjectUtils.isEmpty(car)){
            return ResponseResult.fail();
        }
        try {
            Boolean aBoolean = carService.updateStates(car);
            if (!aBoolean){
                return ResponseResult.fail();
            }
            return ResponseResult.success();
        } catch (Exception e) {
            log.error("更新状态失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 查找出行所有类型
     * @return
     */
    @GetMapping("/typeAll")
    @PreAuthorize("hasAuthority('system:car:typeAll')")
    public ResponseResult typeAll(){
        try {
            List<AnalysisVO> typeAll = carService.typeAll();
            return ResponseResult.success(Optional.ofNullable(typeAll).orElse(Collections.emptyList()));
        } catch (Exception e) {
            log.error("获取所有类型失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 查找出行所有状态数量
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:car:stateAll')")
    public ResponseResult stateAll(){
        try {
            List<AnalysisVO> voList = carService.stateAll();
            return ResponseResult.success(Optional.ofNullable(voList).orElse(Collections.emptyList()));
        } catch (Exception e) {
            log.error("获取所有类型失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 列表条件分页查询出行
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody CarQuery query){
        try {
            Page<Car> result = carService.listPage(query);
            if (result == null || CollectionUtils.isEmpty(result.getRecords())) {
                return  ResponseResult.success( Collections.emptyList());
            }
            return ResponseResult.success(result.getRecords(), result.getTotal());
        } catch (Exception e) {
            log.error("获取列表失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }
}

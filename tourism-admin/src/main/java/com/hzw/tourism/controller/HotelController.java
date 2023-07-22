package com.hzw.tourism.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.vo.HotelVo;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Hotel;
import com.hzw.tourism.qo.HotelQuery;
import com.hzw.tourism.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @since 2023-01-19
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {
    private final static Logger log= LoggerFactory.getLogger(HotelController.class);
    @Autowired
    private HotelService hotelService;

    /**
     * 查看发布酒店数量
     * @return
     */

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('system:hotel:count')")
    public ResponseResult count0() throws GlobalExceptionHandler {
            return ResponseResult.success(hotelService.count0());

    }
    @GetMapping("/count1")
    @PreAuthorize("hasAuthority('system:hotel:count1')")
    public ResponseResult count1(){
        try {
            Integer count = hotelService.count1();
            return ResponseResult.success(count);
        } catch (Exception e) {
            log.error("失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }


    /**
     * 新增或修改酒店方式
     * @param hotel
     * @return
     */
    @PostMapping("/saveOrUpdateHotel")
    @PreAuthorize("hasAuthority('system:hotel:saveOrUpdateHotel')")
    public ResponseResult saveOrUpdateHotel(@RequestBody Hotel hotel) {
        if (ObjectUtils.isEmpty(hotel)){
            return ResponseResult.fail();
        }
        try {
            hotelService.saveOrUpdateHotel(hotel);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error("失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }
    /**
     * 通过id查询酒店方式
     * @param id
     * @return
     */
    @GetMapping("/findHotelById")
    public ResponseResult findHotelById(@RequestParam Long id){
        try {
            Assert.notNull(id,"id不能为空");
            Hotel hotel = hotelService.findHotelById(id);
            return ResponseResult.success(hotel);
        } catch (Exception e) {
            log.error("失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }

    }

    /**
     * 工具id删除酒店信息
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:hotel:deleteById')")
    public ResponseResult deleteById(Long id) throws GlobalExceptionHandler{
            Assert.notNull(id,"id不能为空");
            hotelService.deleteById(id);
            return ResponseResult.success();

    }

    /**
     * 根据id修改酒店状态
     * @param hotel
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:hotel:updateStates')")
    public ResponseResult updateStates(@RequestBody Hotel hotel) {
        if (ObjectUtils.isEmpty(hotel)) {
            return ResponseResult.fail();
        }
        try {
            return ResponseResult.fail().isSuccess(hotelService.updateStates(hotel),"更新状态");
        } catch (Exception e) {
            log.error("失败={}",e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 查找酒店所有类型
     * @return
     */
    @GetMapping("/typeAll")
    @PreAuthorize("hasAuthority('system:hotel:typeAll')")
    public ResponseResult typeAll() throws GlobalExceptionHandler {
        return ResponseResult.success(hotelService.typeAll());
    }

    /**
     * 查找酒店所有状态数量
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:hotel:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler{
        return ResponseResult.success(hotelService.stateAll());
    }
    
    /**
     * 列表条件分页查询酒店
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody HotelQuery query) throws GlobalExceptionHandler{
        Page<Hotel> result = hotelService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());
    }
    /**
     * 景区附近酒店
     * @param
     * @return
     */
    @GetMapping("/listHotelSpot")
    public ResponseResult listHotelSpot(Long scenicSpotId) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(scenicSpotId)){
            return ResponseResult.fail();
        }
        return ResponseResult.success( hotelService.listHotelSpot(scenicSpotId));
    }

    /**
     * 酒店附近景区
     * @param
     * @return
     */
    @GetMapping("/listSpotHotel")
    public ResponseResult listSpotHotel(Long scenicSpotId) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(scenicSpotId)){
            return ResponseResult.fail();
        }
        return ResponseResult.success( hotelService.listSpotHotel(scenicSpotId));
    }

    @GetMapping("/getHotelSpot")
    public  List<HotelVo>  getHotelSpot(){
        return hotelService.getHotelSpot();
    }


}

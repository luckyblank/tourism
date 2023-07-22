package com.hzw.tourism.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Order;
import com.hzw.tourism.DTO.OrderDTO;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.qo.OrderQuery;
import com.hzw.tourism.qo.UserOrderQuery;
import com.hzw.tourism.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 预定生成订单信息
     * @param orderDto
     * @return
     */
    @PostMapping("/userOrder")
    public ResponseResult userOrder(@RequestBody OrderDTO orderDto) throws GlobalExceptionHandler {
        //1.判断是否传有订单信息
        if (ObjectUtils.isEmpty(orderDto)) {
            //1.1否返回错误信息
            return ResponseResult.fail();
        }
         return ResponseResult.success(orderService.userOrder(orderDto));
    }

    /**
     * 查看发布订单数量
     *
     * @return
     */

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('system:order:count')")
    public ResponseResult count0() throws GlobalExceptionHandler {
        return ResponseResult.success(orderService.count0());
    }

    @GetMapping("/count1")
    @PreAuthorize("hasAuthority('system:order:count1')")
    public ResponseResult count1() throws GlobalExceptionHandler {
        return ResponseResult.success(orderService.count1());
    }


    @PostMapping("/toBePaid")
    public ResponseResult toBePaid(@RequestBody Order order) {
        if (ObjectUtils.isEmpty(order)){
            return ResponseResult.fail();
        }
        try {
            Boolean isSuccess = orderService.toBePaid(order);
            return ResponseResult.isSuccess(isSuccess,"支付");
        } catch (GlobalExceptionMyHandler g) {
            return ResponseResult.fail(g.getCustomMessage());
        }
    }

    /**
     * 取消订单
     * @param order
     * @return
     */
    @PostMapping("/callOff")
    public ResponseResult callOff(@RequestBody Order order) throws GlobalExceptionHandler {
        return ResponseResult.isSuccess(orderService.callOff(order),"取消订单");
    }

    /**
     * 退款
     * @param order
     * @return
     */
    @PostMapping("/refunded")
    public ResponseResult refunded(@RequestBody Order order) throws GlobalExceptionHandler {
        return ResponseResult.isSuccess( orderService.refunded(order),"退款");

    }

    /**
     * 用户删除订单
     * @param id
     * @return
     */
    @GetMapping("/deleteByUser")
    public ResponseResult deleteByUser(Long id) throws GlobalExceptionHandler{
        if (ObjectUtils.isEmpty(id)){
            return ResponseResult.fail("id不能为null");
        }
        return ResponseResult.isSuccess(orderService.deleteByUser(id),"删除订单");

    }

    /**
     * 修改订单信息
     *
     * @param order
     * @return
     */
    @PostMapping("/updateOrder")
    @PreAuthorize("hasAuthority('system:order:updateOrder')")
    public ResponseResult saveOrUpdateOrder(@RequestBody Order order) {
        if (ObjectUtils.isEmpty(order)){
            return ResponseResult.fail("order不能为null");
        }

        try {
            Boolean isSuccess = orderService.updateOrder(order);
            return ResponseResult.isSuccess(isSuccess,"更新订单失败");
        } catch (GlobalExceptionMyHandler globalExceptionMyHandler) {
           return ResponseResult.fail(globalExceptionMyHandler.getCustomMessage());
        }
    }

    /**
     * 通过id查询订单
     *
     * @param id
     * @return
     */
    @GetMapping("/findOrderById")
    public ResponseResult findOrderById(@RequestParam Long id) {
        try {
            Assert.notNull(id,"id不能为null");
            Order order = orderService.findOrderById(id);
            return ResponseResult.success(order);
        } catch (GlobalExceptionMyHandler globalExceptionMyHandler) {
            return ResponseResult.fail(globalExceptionMyHandler.getCustomMessage());
        }

    }


    /**
     * 根据订单编号删除订单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public ResponseResult deleteById(Long id)  throws GlobalExceptionHandler{
        Assert.notNull(id,"id不能为null");
        Boolean isSuccess = orderService.deleteById(id);
        return ResponseResult.isSuccess(isSuccess,"删除订单");
    }


    /**
     * 根据id修改支付订单信息
     *
     * @param order
     * @return
     */
    @PostMapping("/updateStates")
    public ResponseResult updateStates(@RequestBody Order order) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(order)){
            return ResponseResult.fail();
        }
        Boolean aBoolean = orderService.updateStates(order);
        return ResponseResult.isSuccess(aBoolean,"修改订单");
    }

    /**
     * 查找订单所有类型
     *
     * @return
     */
    @GetMapping("/productTypeALL")
    @PreAuthorize("hasAuthority('system:order:productTypeALL')")
    public ResponseResult typeAll() throws GlobalExceptionHandler {
        return ResponseResult.success(orderService.typeAll());

    }

    /**
     * 查找所有状态数量
     *
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:order:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler {
        return ResponseResult.success(orderService.stateAll());

    }


    /**
     * 查找所有已支付的订单
     *
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll() throws GlobalExceptionHandler {
        return ResponseResult.success( orderService.findAll());
    }

    /**
     * 列表条件分页查询订单
     *
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody OrderQuery query) throws GlobalExceptionHandler {
        IPage<Order> result = orderService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());
    }

    /**
     * 条件分页查询订单
     *
     * @param query
     * @return
     */
    @PostMapping("/orderListPage")
    public ResponseResult orderListPage(@RequestBody UserOrderQuery query) throws GlobalExceptionHandler
    {
        IPage<Order> result = orderService.orderListPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
       return ResponseResult.success(result.getRecords(),result.getTotal());
    }
}

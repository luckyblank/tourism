package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.DTO.OrderDTO;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.*;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.constant.RedisConstants;
import com.hzw.tourism.service.*;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.mapper.OrderMapper;
import com.hzw.tourism.qo.OrderQuery;
import com.hzw.tourism.qo.UserOrderQuery;
import com.hzw.tourism.utils.RedisIdWorker;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-21
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CarService carService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private RoutelService routelService;
    @Autowired
    private ScenicSpotService scenicSpotService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private InsuranceUserInfoService insuranceUserInfoService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisIdWorker redisIdWorker;


    @Override
    public Long userOrder(OrderDTO orderDto) {
        Integer productType = orderDto.getOrderType();
        Long productId = orderDto.getProductId();
        //2.1生成随机订单号
        long orderId = redisIdWorker.nextId("order");
        //订单类型车票
     switch (productType){
         case 0:
             Car car = carService.getById(productId);
             String title = car.getTitle();
             Double price = car.getPrice();
             String imgUrl4 = car.getImgUrl();
             orderInfo(orderDto,title,orderId,price,imgUrl4);
             break;
         case 1:
             Hotel hotel = hotelService.getById(productId);
             String hotelName = hotel.getHotelName();
             Double price1 = hotel.getPrice();
             String imgUrl3 = hotel.getImgUrl();
             orderInfo(orderDto,hotelName,orderId,price1,imgUrl3);
             break;
         case 2:
             Routel routel = routelService.getById(productId);
             String routelTitle = routel.getTitle();
             Double price2 = routel.getPrice();
             String imgUrl2 = routel.getImgUrl();
             orderInfo(orderDto,routelTitle,orderId,price2,imgUrl2);
             break;
         case 3:
             ScenicSpot scenicSpot = scenicSpotService.getById(productId);
             String spotName = scenicSpot.getSpotName();
             Double price3 = scenicSpot.getTicketsMessage();
             String imgUrl1 = scenicSpot.getImgUrl();
             orderInfo(orderDto,spotName,orderId,price3,imgUrl1);
             break;
         case 4:
             Insurance insurance = insuranceService.getById(productId);
             String insuranceTitle = insurance.getTitle();
             Double price4 = insurance.getPrice();
             String imgUrl = insurance.getImgUrl();
             orderInfo(orderDto,insuranceTitle,orderId,price4,imgUrl);
             InsuranceUserInfo userInfo = new InsuranceUserInfo();
             //封装用户保险信息
             userInfo.setAddTiem(LocalDateTime.now());
             Long userId = WebUtils.getUserId();
             Admin user = adminService.getById(userId);
             userInfo.setIcCode(user.getIcCode());
             userInfo.setInsuranceId(productId);
             userInfo.setUserName(user.getName());
             userInfo.setType(insurance.getType());
             userInfo.setUserId(userId);
             userInfo.setInsuranceCompany(insurance.getInsuranceCompany());
             insuranceUserInfoService.save(userInfo);
             break;
             default:
                 log.error("没有该订单类型！！！！");
                 break;
     }
        //3.返回订单号
        return orderId;

    }


    public  ResponseResult orderInfo(OrderDTO orderDto,String title,Long orderId,Double price,String imgUrl) {
        System.err.println(orderDto.getSetoffTime());
        Long userId = WebUtils.getUserId();
        Integer productType = orderDto.getOrderType();
        Long productId = orderDto.getProductId();
        Integer peopleCount = orderDto.getPeopleCount();
        String linkTel = orderDto.getLinkTel();
        String name = orderDto.getName();
       // orderDto.setSetoffTime(new Date());
        Date date = new Date();
        String orderCode = UUID.randomUUID().toString();
        Admin user = adminService.getById(userId);
        Order order = new Order();
        order.setId(orderId);
        order.setAddTime(date);
        order.setUserId(userId);
            order.setSetoffTime(orderDto.getSetoffTime());
     
        order.setName(name);
        order.setIcCode(user.getIcCode());
        order.setOrderCode(orderCode);
        order.setOrderTime(date);
        order.setLinkTel(linkTel);
        if (orderDto.getPrice()!=null){
            order.setFee(orderDto.getPrice());
        }else {
            order.setFee(price);
        }
        if (orderDto.getDay()!=null){
            order.setDay(orderDto.getDay());
        }
        order.setPeopleCount(peopleCount);
        order.setRequirement(orderDto.getRequirement());
        order.setProductName(title);
        order.setProductId(productId);
        order.setProductType(productType);
        order.setUserName(user.getUsername());
        if (imgUrl!=null){
            order.setImgUrl(imgUrl);
        }
        log.info("订单信息：{}",order);
        boolean save = save(order);
        if (!save) {
            return new ResponseResult(400, "生成订单信息失败");
        }
        return new ResponseResult(200,"生成订单信息成功！",orderId);
    }



    /**
     * 查找所有订单数量
     *
     * @return
     */
    @Override
    public Integer count0() {
        String key= RedisConstants.CACHE_ORDER_COUNT0_KEY;
        if (redisTemplate.hasKey(key)) {
            String count = redisTemplate.opsForValue().get(key);
            Integer count0 = (Integer) JSON.parse(count);
            log.info("走缓存.....");
            return count0;
        } else {
            Integer count2 = lambdaQuery().count();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count2), CommonConstants.FOUR, TimeUnit.MINUTES);
            return count2;
        }
    }

    /**
     * 查找所有已支付订单
     *
     * @return
     */
    @Transactional
    @Override
    public Boolean toBePaid(Order order) {
        Date date = new Date();
        order.setModifyTime(date);
        order.setPay_time(date);
        if (order.getState()!=0){
           throw new GlobalExceptionMyHandler("该订单已无法操作支付");
        }
        if (order.getProductType()==4){
            LambdaUpdateWrapper<Order> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
            lambdaQueryWrapper1.eq(Order::getId, order.getId()).set(Order::getState, 1);
            boolean update = update(lambdaQueryWrapper1);
            if (update){
                insuranceUserInfoService.update().set("state",1).eq("insurance_id",order.getProductId()).update();
            }
            return update;
        }
        LambdaUpdateWrapper<Order> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper1.eq(Order::getId, order.getId()).set(Order::getState, 1);
        boolean update = update(lambdaQueryWrapper1);
        return update;
    }


    @Override
    public Boolean refunded(Order order) {
        Date date = new Date();
        order.setModifyTime(date);
        order.setPay_time(date);
        boolean isSuccess=false;
        if (order.getState()==1){
            LambdaUpdateWrapper<Order> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
            lambdaQueryWrapper1.eq(Order::getId, order.getId()).set(Order::getState, 4);
            isSuccess = update(lambdaQueryWrapper1);
            return isSuccess;
        }
        return isSuccess;

    }


    @Override
    public Boolean callOff(Order order) {
        Date date = new Date();
        order.setModifyTime(date);
        order.setPay_time(date);
        LambdaUpdateWrapper<Order> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper1.eq(Order::getId, order.getId()).set(Order::getState, 2);
        boolean update = update(lambdaQueryWrapper1);
        return update;
    }

    @Override
    public IPage<Order> orderListPage(UserOrderQuery query) {
        Long userId = WebUtils.getUserId();
        Page<Order> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getUserId,userId);
        lambdaQueryWrapper.eq(query.getState()!=null,Order::getState, query.getState());
        lambdaQueryWrapper.eq(query.getProductType()!=null,Order::getProductType,query.getProductType());
        //分页
        IPage<Order> result = orderMapper.selectPage(page, lambdaQueryWrapper);
        return result;
         }

    /**
     * 查找所有已支付订单数量
     *
     * @return
     */
    @Override
    public Integer count1() {
        String key= RedisConstants.CACHE_ORDER_COUNT1_KEY;
        if (redisTemplate.hasKey(key)) {
            String count = redisTemplate.opsForValue().get(key);
            Integer count0 = (Integer) JSON.parse(count);
            log.info("走缓存.....");
            return count0;
        } else {
            Integer count2 = lambdaQuery().eq(Order::getState, 1).count();
            if (count2 == null) {
                return count2;
            }
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count2), CommonConstants.FOUR, TimeUnit.MINUTES);
            return count2;
        }
    }

    @Override
    public Boolean updateOrder(Order order) throws GlobalExceptionMyHandler {
        Date date = new Date();
            order.setModifyTime(date);
            if (order.getState()==2||order.getState()==4){
                throw new GlobalExceptionMyHandler("已取消、已退款不能修改订单信息");
            }
            order.setModifyTime(date);
            order.setModifyUserId(WebUtils.getUserId());
        boolean b = updateById(order);
        return b;
    }

    @Override
    public Order findOrderById(Long id) throws GlobalExceptionMyHandler {
        String key= RedisConstants.CACHE_ORDER_KEY+id;
        String orderById = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(orderById)) {
            Order orders = JSON.parseObject(orderById, Order.class);
            log.info("读缓存.....");
            return orders;
        }
        if ("".equals(orderById)){
           throw new GlobalExceptionMyHandler("该订单信息不存在");
        }
            Order order = orderMapper.selectById(id);
            if (order == null) {
                redisTemplate.opsForValue().set(key,"",2,TimeUnit.MINUTES);
                throw new GlobalExceptionMyHandler("该订单信息不存在");
            }
            redisTemplate.opsForValue().set("order:findOrderById" + id, JSON.toJSONString(order), 4, TimeUnit.HOURS);
            log.info("存入缓存....");
            return order;
    }

    @Override
    public Boolean deleteById(Long id)  {
        String count0Key= RedisConstants.CACHE_ORDER_COUNT0_KEY;
        String count1Key= RedisConstants.CACHE_ORDER_COUNT1_KEY;
        String orderKey= RedisConstants.CACHE_ORDER_KEY+id;
        Boolean delete = deleteById(id);
        redisTemplate.delete(count0Key);
        redisTemplate.delete(count1Key);
        redisTemplate.delete(orderKey);
        return delete;
    }
    @Override
    public Boolean deleteByUser(Long id) {
        Long userId = WebUtils.getUserId();
        String count0Key= RedisConstants.CACHE_ORDER_COUNT0_KEY;
        String count1Key= RedisConstants.CACHE_ORDER_COUNT1_KEY;
        String orderKey= RedisConstants.CACHE_ORDER_KEY+id;
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(id!=null,Order::getId,id);
        lambdaQueryWrapper.eq(userId!=null,Order::getUserId,userId);
        boolean remove = remove(lambdaQueryWrapper);
        redisTemplate.delete(count0Key);
        redisTemplate.delete(count1Key);
        redisTemplate.delete(orderKey);
        return remove;
    }



    @Override
    public Boolean updateStates(Order order) {
        String key= RedisConstants.CACHE_ORDER_COUNT1_KEY;
        Date date = new Date();
        order.setModifyTime(date);
        order.setModifyUserId(WebUtils.getUserId());
        LambdaUpdateWrapper<Order> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper1.eq(Order::getId, order.getId()).set(Order::getState, order.getState());
        boolean update = update(lambdaQueryWrapper1);
        redisTemplate.delete(key);
        return update;
    }

    @Override
    public List<AnalysisVO> typeAll() {
        Integer ticketCount = lambdaQuery().eq(Order::getProductType, 0).count();
        Integer hotelCount = lambdaQuery().eq(Order::getProductType, 1).count();
        Integer routeCount = lambdaQuery().eq(Order::getProductType, 2).count();
        Integer scenicSpotCount = lambdaQuery().eq(Order::getProductType, 3).count();
        Integer insuranceCount = lambdaQuery().eq(Order::getProductType, 4).count();
        AnalysisVO analysisUnpaid= new AnalysisVO("车票", ticketCount);
        AnalysisVO analysisHavePaid = new AnalysisVO("酒店", hotelCount);
        AnalysisVO analysisCancelled= new AnalysisVO("路线", routeCount);
        AnalysisVO analysisUnderRefund= new AnalysisVO("景点", scenicSpotCount);
        AnalysisVO analysisRefunded= new AnalysisVO("保险", insuranceCount);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisCancelled);
        analysisVOS.add(analysisHavePaid);
        analysisVOS.add(analysisRefunded);
        analysisVOS.add(analysisUnderRefund);
        analysisVOS.add(analysisUnpaid);
        return analysisVOS;


    }

    @Override
    public List<AnalysisVO>  stateAll() {
        //订单状态，0：未支付；1：已支付；2：已取消；3：退款中；4：已退款
        Integer unpaid = lambdaQuery().eq(Order::getState, 0).count();
        Integer havePaid = lambdaQuery().eq(Order::getState, 1).count();
        Integer cancelled = lambdaQuery().eq(Order::getState, 2).count();
        Integer underRefund = lambdaQuery().eq(Order::getState, 3).count();
        Integer refunded = lambdaQuery().eq(Order::getState, 4).count();
        AnalysisVO analysisUnpaid= new AnalysisVO("未支付", unpaid);
        AnalysisVO analysisHavePaid = new AnalysisVO("已支付", havePaid);
        AnalysisVO analysisCancelled= new AnalysisVO("已取消", cancelled);
    //   AnalysisVO analysisUnderRefund= new AnalysisVO("保险处理中", underRefund);
        AnalysisVO analysisRefunded= new AnalysisVO("已退款", refunded);
        ArrayList<AnalysisVO> analysisVOS = new ArrayList<>();
        analysisVOS.add(analysisCancelled);
        analysisVOS.add(analysisHavePaid);
        analysisVOS.add(analysisRefunded);
     //   analysisVOS.add(analysisUnderRefund);
        analysisVOS.add(analysisUnpaid);
        return analysisVOS;


    }

    @Override
    public  List<Order> findAll() {
        String key= RedisConstants.CACHE_ORDERALLTOBYPAY_KEY;
        String orderAll = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(orderAll)) {
            List<Order> orderList = JSON.parseArray(orderAll, Order.class);
            log.info("走缓存.....");
            return Optional.ofNullable(orderList).orElse(Collections.emptyList());
        } else {
            List<Order> list = lambdaQuery().eq(Order::getState, 1).list();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(list), CommonConstants.ONE, TimeUnit.HOURS);
            log.info("存入缓存.....");
            return Optional.ofNullable(list).orElse(Collections.emptyList());
        }
    }

    @Override
    public IPage<Order> listPage(OrderQuery query) {
        //分页
        Page<Order> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getUserName()),Order::getUserName, query.getUserName());
        lambdaQueryWrapper.eq(query.getProductType()!=null,Order::getProductType, query.getProductType());
        lambdaQueryWrapper.eq(query.getUserId()!=null,Order::getUserId, query.getUserId());

        //分页
        IPage<Order> result = orderMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }


}

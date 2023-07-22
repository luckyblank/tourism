package com.hzw.tourism.constant;

public class RedisConstants {

    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final String LOGIN_USER_KEY = "login:token:";
//出行
    public static final String CACHE_CAR_COUNT0_KEY = "cache:car:count0";
    public static final String CACHE_CAR_KEY = "cache:car:";
    /**
     * 酒店
     */
    public static final String CACHE_HOTEL_COUNT0_KEY = "cache:hotel:count0";
    public static final String CACHE_HOTEL_COUNT1_KEY = "cache:hotel:count1";
    public static final String CACHE_HOTEL_KEY = "cache:hotel:";
    /**
     * 保险
     */
    public static final String CACHE_INSURANCE_COUNT0_KEY = "cache:insurance:count0";
    public static final String CACHE_INSURANCE_COUNT1_KEY = "cache:insurance:count1";
    public static final String CACHE_INSURANCE_KEY = "cache:insurance:";
/**
 * 留言
 */
    public static final String CACHE__MESSAGE_COUNT0_KEY = "cache:message:count0";
    public static final String CACHE_MESSAGE__COUNT1_KEY = "cache:message:count1";
    public static final String CACHE_MESSAGE__KEY = "cache:message:";

    /**
     * 攻略
     */
    public static final String CACHE_STRATEGY_COUNT0_KEY = "cache:strategy:count0";
    public static final String CACHE_STRATEGY__COUNT1_KEY = "cache:strategy:findAll";
    public static final String CACHE_STRATEGY_FINDALL_KEY = "cache:strategy:isAlive:";
    public static final String CACHE_STRATEGY_KEY = "cache:strategy:";
    /**
     *景点
     */
    public static final String CACHE__SCENIC_COUNT0_KEY = "cache:scenic:count0";
    public static final String CACHE_SCENIC__COUNT1_KEY = "cache:scenic:count1";
    public static final String CACHE_SCENIC_KEY = "cache:scenic:";

    /**
     *路线
     */
    public static final String CACHE__ROUTE_COUNT0_KEY = "cache:route:count0";
    public static final String CACHE_ROUTE_COUNT1_KEY = "cache:route:count1";
    public static final String CACHE_ROUTE_KEY = "cache:route:";
    public static final String CACHE_ROUTEALL_KEY = "cache:route:findAll";

    /**
     *订单
     */
    public static final String CACHE_ORDER_COUNT0_KEY = "cache:order:count0";
    public static final String CACHE_ORDER_COUNT1_KEY = "cache:order:count1";
    public static final String CACHE_ORDER_KEY = "cache:order:";
    public static final String CACHE_ORDERALLTOBYPAY_KEY = "cache:order:findAllToByPay";
    /**
     *用户
     */
    public static final String CACHE_ADMIN_COUNT0_KEY = "cache:admin:count0";
    public static final String CACHE_ADMIN_COUNT1_KEY = "cache:admin:count1";
    public static final String CACHE_ADMIN_KEY = "cache:admin:";
    public static final String CACHE_ADMIN_FINDALLUSER_KEY = "cache:admin:findAllUser";
/**
 * GIVEALIKE COLLECT 评论
 */
    public static final String CACHE_GIVEALIKE_KEY = "user:isLike:";
    public static final String CACHE_COLLECT_KEY = "user:collect:";
}

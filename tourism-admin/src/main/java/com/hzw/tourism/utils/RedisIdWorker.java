package com.hzw.tourism.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class RedisIdWorker {
    /**
     * 开始时间戳
     */
    private static final long BEGIN_TIMESTAMP = 167516;
    /**
     * 序列号的位数
     */
    private static final int COUNT_BITS = 32;

    private StringRedisTemplate stringRedisTemplate;

    public RedisIdWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public  long nextId(String keyPrefix) {
        // 1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        return nowSecond;
//        long timestamp = nowSecond - BEGIN_TIMESTAMP;
//
//        // 2.生成序列号
//        // 2.1.获取当前日期，精确到天
//        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
//        // 2.2.自增长
 //      long count = stringRedisTemplate.opsForValue().increment("order:" + keyPrefix + ":" + );
//
//        // 3.拼接并返回  移动31位
//        return timestamp << COUNT_BITS | count;
    }

    public static void main(String[] args) {
        //生成当前时间戳
        LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 31, 11, 37);
        long second = localDateTime.toEpochSecond(ZoneOffset.UTC);
        // System.out.println(localDateTime);
        String string = UUID.randomUUID().toString();
        System.out.println(string);
        System.out.println(second);

    }






}

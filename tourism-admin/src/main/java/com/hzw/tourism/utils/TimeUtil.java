package com.hzw.tourism.utils;

import cn.hutool.core.date.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String TimeToDate(String currentTime ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间戳
        //long l = System.currentTimeMillis();
        //如果你数据库存储的时间戳类型为string，就需要将string字符串转为long类型
        long l = Long.parseLong(currentTime);
        String format = sdf.format(l);
        return format;
    }
}

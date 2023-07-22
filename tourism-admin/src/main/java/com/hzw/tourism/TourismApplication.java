package com.hzw.tourism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@MapperScan("com.hzw.tourism.mapper")
@SpringBootApplication
public class TourismApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TourismApplication.class, args);

    }

}

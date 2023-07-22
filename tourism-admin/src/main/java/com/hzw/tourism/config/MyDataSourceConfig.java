/*
 * Copyright (C), 2022-2023, Freedom Person
 * FileName: MyDataSourceConfig.java
 * Author:   lucky
 * Date:     2023/7/22 17:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.hzw.tourism.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 功能描述:<br>
 *
 * @author lucky
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Configuration
public class MyDataSourceConfig {


    //默认的自动配置是判断容器中没有定义数据源，才会自动配置HikariDataSource的数据源

    //将dataSource与配置文件中的属性进行绑定
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() {


        DruidDataSource druidDataSource = new DruidDataSource();


        return druidDataSource;
    }

    /**
     * 配置druid的监控页功能
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();

        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        return registrationBean;
    }
}

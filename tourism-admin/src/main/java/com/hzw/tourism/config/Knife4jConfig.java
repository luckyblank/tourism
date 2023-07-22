/*
 * Copyright (C), 2022-2023, Freedom Person
 * FileName: Knife4jConfig.java
 * Author:   lucky
 * Date:     2023/7/21 23:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.hzw.tourism.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:<br>
 *
 * @author lucky
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@Configuration
@EnableSwagger2
public class Knife4jConfig {
    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("Tourism APIs测试")
                        .termsOfServiceUrl("http://localhost:8083/tourism/")
                        .contact(new Contact("Hungziwei","",""))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("tourism")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hzw.tourism.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public BeanPostProcessor generateBeanPostProcessor(){
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}

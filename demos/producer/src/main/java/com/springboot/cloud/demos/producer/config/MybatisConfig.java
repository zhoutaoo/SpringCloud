package com.springboot.cloud.demos.producer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 初使化Mybatis审计字段自动赋值的interceptor
 */
@Configuration
@ComponentScan(basePackageClasses = com.springboot.common.web.interceptor.AuditInterceptor.class)
@MapperScan("com.springboot.cloud.demos.producer.dao")
public class MybatisConfig {
}

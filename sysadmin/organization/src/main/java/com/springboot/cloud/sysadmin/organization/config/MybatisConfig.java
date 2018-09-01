package com.springboot.cloud.sysadmin.organization.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 初使化Mybatis审计字段自动赋值的interceptor
 */
@Configuration
@ComponentScan(basePackageClasses = com.springboot.common.web.interceptor.AuditInterceptor.class)
public class MybatisConfig {
}

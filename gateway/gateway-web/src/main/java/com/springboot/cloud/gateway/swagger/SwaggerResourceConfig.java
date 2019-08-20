package com.springboot.cloud.gateway.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Create by kim
 * on 2019-08-15 18:09
 * 需要展示的swagger服务名称配置
 * version 1.0
 */
@Data
@Component
@ConfigurationProperties("swagger.server")
@RefreshScope
public class SwaggerResourceConfig {
    private String resource;
}

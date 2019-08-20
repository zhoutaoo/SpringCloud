package com.springboot.cloud.gateway.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by kim
 * on 2019-08-15 12:05
 * swagger文档配置加载
 * version 1.0
 */
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {
    /**
     * swagger2默认的url后缀
     */
    public static final String SWAGGER2URL = "/v2/api-docs";

    /**
     * 默认可以从注册中心获取网关路由
     */
    private final RouteLocator routeLocator;

    /**
     * 从配置文件获取需要聚合文档的服务
     */
    @Autowired
    private SwaggerResourceConfig swaggerPassRoutesConfig;

    @Autowired
    public SwaggerProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<String> routes= Stream.of(swaggerPassRoutesConfig.getResource().split(",")).collect(Collectors.toList());
        List<SwaggerResource> resources = new ArrayList<>();
        routes.forEach(serverId -> {
            String url = "/" + serverId + SWAGGER2URL;
            resources.add(swaggerResource(serverId,url));
        });
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;

    }


}


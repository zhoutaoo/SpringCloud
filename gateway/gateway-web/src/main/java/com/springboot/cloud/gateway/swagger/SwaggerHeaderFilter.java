package com.springboot.cloud.gateway.swagger;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;


/**
 * Create by kim
 * on 2019-08-17 15:53
 * 解决RELEASE版本swagger路径问题
 * SR2版本无需实现
 * version 1.0
 */
@Component
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";
    private static final String HOST_NAME = "X-Forwarded-Host";

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.SWAGGER2URL)) {
                return chain.filter(exchange);
            }

            String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.SWAGGER2URL));
            String host = request.getHeaders().getFirst("Host");
            ServerHttpRequest newRequest = request.mutate()
                    .header(HEADER_NAME, basePath)
                    .header(HOST_NAME, host)
                    .build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };

    }
}

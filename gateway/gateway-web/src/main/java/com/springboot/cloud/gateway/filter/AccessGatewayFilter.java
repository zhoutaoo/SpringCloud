package com.springboot.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 主要负责jwt token的初步校验
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    /**
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("check jwt token");
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethod().toString();
        HttpHeaders headers = request.getHeaders();
        log.debug("headers:{},{}", method, headers);
        return chain.filter(exchange);
    }
}

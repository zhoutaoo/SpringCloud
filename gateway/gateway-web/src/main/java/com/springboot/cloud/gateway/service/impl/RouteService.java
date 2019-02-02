package com.springboot.cloud.gateway.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.springboot.cloud.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class RouteService implements IRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    private void loadRouteDefinition() {
        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");

        if (CollectionUtils.isEmpty(gatewayKeys)) {
            return;
        }

        List<String> gatewayRoutes = Optional.ofNullable(stringRedisTemplate.opsForValue().multiGet(gatewayKeys)).orElse(Lists.newArrayList());
        gatewayRoutes.forEach(value -> {
            try {
                RouteDefinition routeDefinition = new ObjectMapper().readValue(value, RouteDefinition.class);
                routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        loadRouteDefinition();
        return Flux.fromIterable(routeDefinitionMaps.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono) {
        return routeDefinitionMono.flatMap(routeDefinition -> {
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeDefinitionMaps.remove(id);
            return Mono.empty();
        });
    }
}

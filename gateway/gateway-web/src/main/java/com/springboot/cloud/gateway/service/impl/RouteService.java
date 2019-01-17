package com.springboot.cloud.gateway.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.gateway.entity.po.GatewayRoute;
import com.springboot.cloud.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class RouteService implements IRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    @PostConstruct
    private void init() {

        ObjectMapper objectMapper = new ObjectMapper();

        //stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinition.class));

        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");

        stringRedisTemplate.opsForValue().multiGet(gatewayKeys).forEach(routeDefinition -> {
            try {
                GatewayRoute gatewayRoute = objectMapper.readValue(routeDefinition, GatewayRoute.class);
                routeDefinitionMaps.put(gatewayRoute.getRouteId(), convert(gatewayRoute));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeDefinitionMaps.values());
    }

    private RouteDefinition convert(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        return routeDefinition;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono) {
        return routeDefinitionMono.flatMap(routeDefinition -> {
            stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES + routeDefinition.getId(), routeDefinition.toString());
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            stringRedisTemplate.delete(GATEWAY_ROUTES + id);
            return Mono.empty();
        });
    }
}

package com.springboot.cloud.gateway.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RouteService implements IRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes*";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinition.class));
        HashOperations hashOperations = redisTemplate.opsForHash();

        List values = hashOperations.values(GATEWAY_ROUTES);

        ScanOptions scanOptions = ScanOptions.scanOptions().match(GATEWAY_ROUTES).build();
        Cursor<Object> curosr = redisTemplate.opsForSet().scan(GATEWAY_ROUTES, scanOptions);

        while (curosr.hasNext()) {
            System.out.println(curosr.next());
        }

        values.stream().forEach(routeDefinition -> {
            try {
                routeDefinitions.add(objectMapper.readValue(routeDefinition.toString(), RouteDefinition.class));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
        return Flux.fromIterable(routeDefinitions);
    }
}

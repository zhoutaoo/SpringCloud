package com.springboot.cloud.gateway.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.springboot.cloud.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteService implements IRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    @PostConstruct
    private void loadRouteDefinition() {
        log.info("loadRouteDefinition, 开始初使化路由");
        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");
        if (CollectionUtils.isEmpty(gatewayKeys)) {
            return;
        }
        log.info("预计初使化路由, gatewayKeys：{}", gatewayKeys);
        // 去掉key的前缀
        Set<String> gatewayKeyIds = gatewayKeys.stream().map(key -> {
            return key.replace(GATEWAY_ROUTES, StringUtils.EMPTY);
        }).collect(Collectors.toSet());
        Map<String, RouteDefinition> allRoutes = gatewayRouteCache.getAll(gatewayKeyIds);
        log.info("gatewayKeys：{}", allRoutes);
        routeDefinitionMaps.putAll(allRoutes);
        log.info("共初使化路由信息：{}", routeDefinitionMaps.size());
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMaps.values();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono) {
        return routeDefinitionMono.flatMap(routeDefinition -> {
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            gatewayRouteCache.put(routeDefinition.getId(), routeDefinition);
            log.info("新增路由1条：{}", routeDefinition);
            log.info("目前路由共{}条：", routeDefinitionMaps.size());
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeDefinitionMaps.remove(id);
            gatewayRouteCache.remove(id);
            log.info("删除路由1条：{}", routeId);
            log.info("目前路由共{}条：", routeDefinitionMaps.size());
            return Mono.empty();
        });
    }
}

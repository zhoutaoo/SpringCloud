package com.springboot.cloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono);

    Mono<Void> delete(Mono<String> routeId);
}

package com.springboot.cloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRouteService {
    Flux<RouteDefinition> getRouteDefinitions();

    Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono);

    Mono<Void> delete(Mono<String> routeId);
}

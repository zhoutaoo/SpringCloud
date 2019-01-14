package com.springboot.cloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;

public interface IRouteService {
    Flux<RouteDefinition> getRouteDefinitions();
}

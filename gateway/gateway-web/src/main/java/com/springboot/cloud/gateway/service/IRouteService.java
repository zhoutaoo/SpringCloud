package com.springboot.cloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String routeId);
}

package com.springboot.cloud.gateway.admin.service.impl;

import com.springboot.cloud.gateway.admin.dao.GatewayRouteMapper;
import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import com.springboot.cloud.gateway.admin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GatewayRouteService implements IGatewayRouteService {

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Override
    public long add(GatewayRoute gatewayRoute) {
        return gatewayRouteMapper.insert(gatewayRoute);
    }

    @Override
    @CacheEvict(value = "gatewayRoute", key = "#root.targetClass.name+'-'+#id")
    public void delete(long id) {
        gatewayRouteMapper.delete(id);
    }

    @Override
    @CacheEvict(value = "gatewayRoute", key = "#root.targetClass.name+'-'+#gatewayRoute.id")
    public void update(GatewayRoute gatewayRoute) {
        gatewayRouteMapper.update(gatewayRoute);
    }

    @Override
    @Cacheable(value = "gatewayRoute", key = "#root.targetClass.name+'-'+#id")
    public GatewayRoute get(long id) {
        return gatewayRouteMapper.select(id);
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        return gatewayRouteMapper.query(gatewayRouteQueryParam);
    }
}

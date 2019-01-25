package com.springboot.cloud.gateway.admin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.gateway.admin.dao.GatewayRouteMapper;
import com.springboot.cloud.gateway.admin.entity.ov.GatewayRouteVo;
import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import com.springboot.cloud.gateway.admin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GatewayRouteService implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public long add(GatewayRoute gatewayRoute) {
        long gatewayId = gatewayRouteMapper.insert(gatewayRoute);
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVo(gatewayRoute)));
        return gatewayId;
    }

    @Override
    public void delete(long id) {
        gatewayRouteMapper.delete(id);
        stringRedisTemplate.delete(GATEWAY_ROUTES + id);
    }

    @Override
    public void update(GatewayRoute gatewayRoute) {
        stringRedisTemplate.delete(GATEWAY_ROUTES + gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES, toJson(new GatewayRouteVo(get(gatewayRoute.getId()))));
    }

    @Override
    public GatewayRoute get(long id) {
        return gatewayRouteMapper.select(id);
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        return gatewayRouteMapper.query(gatewayRouteQueryParam);
    }

    @Override
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.query(new GatewayRouteQueryParam());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        gatewayRoutes.forEach(gatewayRoute ->
                opsForValue.set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVo(gatewayRoute)))
        );
        return true;
    }

    /**
     * GatewayRoute转换为json
     *
     * @param gatewayRouteVo redis需要的vo
     * @return json string
     */
    private String toJson(GatewayRouteVo gatewayRouteVo) {
        String routeDefinitionJson = Strings.EMPTY;
        try {
            routeDefinitionJson = new ObjectMapper().writeValueAsString(gatewayRouteVo);
        } catch (JsonProcessingException e) {
            log.error("网关对象序列化为json String", e);
        }
        return routeDefinitionJson;
    }
}

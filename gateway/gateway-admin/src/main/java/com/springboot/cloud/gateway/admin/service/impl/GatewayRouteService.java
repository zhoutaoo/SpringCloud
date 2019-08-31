package com.springboot.cloud.gateway.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.gateway.admin.dao.GatewayRouteMapper;
import com.springboot.cloud.gateway.admin.entity.ov.GatewayRouteVo;
import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import com.springboot.cloud.gateway.admin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    public void delete(String id) {
        gatewayRouteMapper.deleteById(id);
        stringRedisTemplate.delete(GATEWAY_ROUTES + id);
    }

    @Override
    public void update(GatewayRoute gatewayRoute) {
        gatewayRouteMapper.updateById(gatewayRoute);
        stringRedisTemplate.delete(GATEWAY_ROUTES + gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES, toJson(new GatewayRouteVo(get(gatewayRoute.getId()))));
    }

    @Override
    public GatewayRoute get(String id) {
        return gatewayRouteMapper.selectById(id);
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<GatewayRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != gatewayRouteQueryParam.getCreatedTimeStart(), "created_time", gatewayRouteQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != gatewayRouteQueryParam.getCreatedTimeEnd(), "created_time", gatewayRouteQueryParam.getCreatedTimeEnd());
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()), "uri", gatewayRouteQueryParam.getUri());
        return gatewayRouteMapper.selectList(queryWrapper);
    }

    @Override
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.selectList(new QueryWrapper<>());
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

package com.springboot.cloud.gateway.admin.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.gateway.admin.dao.GatewayRouteMapper;
import com.springboot.cloud.gateway.admin.entity.ov.GatewayRouteVo;
import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import com.springboot.cloud.gateway.admin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GatewayRouteService extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @CreateCache(cacheType = CacheType.REMOTE)
    private Cache<String, GatewayRouteVo> gatewayRouteCache;

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.save(gatewayRoute);
        gatewayRouteCache.put(GATEWAY_ROUTES + gatewayRoute.getId(), new GatewayRouteVo(gatewayRoute));
        return isSuccess;
    }

    @Override
    @CacheInvalidate(name = GATEWAY_ROUTES, key = "#id")
    public boolean delete(String id) {
        boolean isSuccess = this.removeById(id);
        gatewayRouteCache.remove(GATEWAY_ROUTES + id);
        return isSuccess;
    }

    @Override
    @CacheInvalidate(name = GATEWAY_ROUTES, key = "#gatewayRoute.id")
    public boolean update(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.updateById(gatewayRoute);
        gatewayRouteCache.put(GATEWAY_ROUTES + gatewayRoute.getId(), new GatewayRouteVo(gatewayRoute));
        return isSuccess;
    }

    @Override
    @Cached(name = GATEWAY_ROUTES, key = "#id", cacheType = CacheType.REMOTE)
    public GatewayRoute get(String id) {
        return this.getById(id);
    }

    @Override
    public List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<GatewayRoute> queryWrapper = gatewayRouteQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()), "uri", gatewayRouteQueryParam.getUri());
        return this.list(queryWrapper).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
    }

    @Override
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = this.list(new QueryWrapper<>());
        gatewayRoutes.forEach(gatewayRoute ->
                gatewayRouteCache.put(GATEWAY_ROUTES + gatewayRoute.getId(), new GatewayRouteVo(gatewayRoute))
        );
        log.info("全局初使化网关路由成功!");
        return true;
    }
}

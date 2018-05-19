package com.springboot.oauth2.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.springboot.oauth2.entity.Resource;
import com.springboot.oauth2.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * <p>
 * 此类在初始化时，应该取到所有资源及其对应code的定义
 * <p>
 */
@Component
@Slf4j
public class FilterInvocationSecurityMetadataSourceImpl extends DefaultFilterInvocationSecurityMetadataSource {
    /**
     * Sets the internal request map from the supplied map. The key elements should be of
     * type {@link RequestMatcher}, which. The path stored in the key will depend on the
     * type of the supplied UrlMatcher.
     *
     * @param requestMap order-preserving map of request definitions to attribute lists
     */
    @Autowired
    public FilterInvocationSecurityMetadataSourceImpl(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        super(requestMap);
        log.debug("constructor:{}", requestMap);
    }

    // According to a URL, Find out permission configuration of this URL.
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        log.info("getAttributes:{}", object);
        return super.getAttributes(object);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        log.info("getAllConfigAttributes:{}");
        return super.getAllConfigAttributes();
    }
}

@Component
@Slf4j
class LoadResourceDefine {

    @Autowired
    private IResourceService resourceService;

    @Bean
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = Maps.newLinkedHashMap();
        Set<Resource> resources = resourceService.findAll();
        for (Resource resource : resources) {
            // url匹配器
            AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher(resource.getUrl(), resource.getMethod());
            // 把资源放入到spring security的resouceMap中
            Collection<ConfigAttribute> configAttributes = Sets.newHashSet(new SecurityConfig(resource.getCode()));
            map.put(pathRequestMatcher, configAttributes);
        }
        log.debug("requestMap:{}", map);
        return map;
    }
}
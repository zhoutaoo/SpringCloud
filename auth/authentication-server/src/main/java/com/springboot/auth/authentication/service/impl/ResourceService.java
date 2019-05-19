package com.springboot.auth.authentication.service.impl;

import com.springboot.auth.authentication.dao.ResourceMapper;
import com.springboot.auth.authentication.entity.Resource;
import com.springboot.auth.authentication.provider.ResourceProvider;
import com.springboot.auth.authentication.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceService implements IResourceService {

    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceProvider resourceProvider;

    /**
     * 系统中所有权限集合
     */
    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    @Override
    public Set<Resource> queryByRoleCodes(String[] roleCodes) {
        return resourceMapper.queryByRoleCodes(roleCodes);
    }

    @Override
    public void addResource(Resource resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
    }

    @Override
    public Map<RequestMatcher, ConfigAttribute> loadResource() {
        Set<Resource> resources = resourceProvider.resources().getData();
        this.resourceConfigAttributes = resources.stream()
                .collect(Collectors.toMap(
                        resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                        resource -> new SecurityConfig(resource.getCode())
                ));
        log.debug("resourceConfigAttributes:{}", this.resourceConfigAttributes);
        return this.resourceConfigAttributes;
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return this.resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> this.resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    /**
     * 创建RequestMatcher
     *
     * @param url
     * @param method
     * @return
     */
    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(mvcHandlerMappingIntrospector, url);
        mvcRequestMatcher.setMethod(HttpMethod.resolve(method));
        return mvcRequestMatcher;
    }
}
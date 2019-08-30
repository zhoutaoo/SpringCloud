package com.springboot.cloud.auth.authentication.config;

import com.springboot.cloud.auth.authentication.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhoutaoo on 2018/5/25.
 */
@Component
class LoadResourceDefine {

    @Autowired
    private IResourceService resourceService;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {
        return resourceService.loadResource();
    }
}

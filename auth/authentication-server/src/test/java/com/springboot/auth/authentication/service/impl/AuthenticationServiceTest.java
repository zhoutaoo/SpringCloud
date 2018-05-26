package com.springboot.auth.authentication.service.impl;

import com.google.common.collect.Sets;
import com.springboot.auth.authentication.entity.Resource;
import com.springboot.auth.authentication.rest.HttpServletRequestAuthWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoutaoo on 2018/5/26.
 */
public class AuthenticationServiceTest {

    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap() {
        {
            MvcRequestMatcher mvcRequestMatcher1 = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users");
            mvcRequestMatcher1.setMethod(HttpMethod.resolve("POST"));
            MvcRequestMatcher mvcRequestMatcher2 = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users/{id}");
            mvcRequestMatcher2.setMethod(HttpMethod.resolve("PUT"));
            MvcRequestMatcher mvcRequestMatcher3 = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users/{id}");
            mvcRequestMatcher3.setMethod(HttpMethod.resolve("DELETE"));
            MvcRequestMatcher mvcRequestMatcher4 = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users/{id}");
            mvcRequestMatcher4.setMethod(HttpMethod.resolve("GET"));
            MvcRequestMatcher mvcRequestMatcher5 = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users/{id}/order");
            mvcRequestMatcher5.setMethod(HttpMethod.resolve("GET"));
            put(mvcRequestMatcher1, new SecurityConfig("user_manager:btn_add"));
            put(mvcRequestMatcher2, new SecurityConfig("user_manager:btn_edit"));
            put(mvcRequestMatcher3, new SecurityConfig("user_manager:btn_del"));
            put(mvcRequestMatcher4, new SecurityConfig("user_manager:view"));
            put(mvcRequestMatcher5, new SecurityConfig("user_order:view"));
        }
    };


    @Test
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求不存在method的资源时_那么返回NONEXISTENT_URL() {
        AuthenticationService authenticationService = new AuthenticationService(this.resourceConfigAttributes);
        ConfigAttribute attributesByUrl = authenticationService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users/1/order", "POST"));
        Assert.assertEquals("NONEXISTENT_URL", attributesByUrl.getAttribute());
    }

    @Test
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求url存在参数时_那么返回匹配的资源信息() {
        AuthenticationService authenticationService = new AuthenticationService(this.resourceConfigAttributes);
        ConfigAttribute attributesByUrl = authenticationService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users/1/order", "GET"));
        Assert.assertEquals("user_order:view", attributesByUrl.getAttribute());
    }

    @Test
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求存在的资源时_那么返回url和method都匹配的资源信息() {
        AuthenticationService authenticationService = new AuthenticationService(this.resourceConfigAttributes);
        ConfigAttribute attributesByUrl = authenticationService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users", "POST"));
        Assert.assertEquals("user_manager:btn_add", attributesByUrl.getAttribute());
    }

    @Test
    public void testIsMatch_假如存在如上资源信息_当给定包含在资源信息时_那么返回true() {
        AuthenticationService authenticationService = new AuthenticationService(null);
        Resource resource = new Resource();
        resource.setCode("user_manager:view");
        Set<Resource> resources = Sets.newHashSet(resource);
        Assert.assertTrue(authenticationService.isMatch(new SecurityConfig("user_manager:view"), resources));
    }

    @Test
    public void testIsMatch_假如存在如上资源信息_当给不包含在资源信息时_那么返回false() {
        AuthenticationService authenticationService = new AuthenticationService(null);
        Resource resource = new Resource();
        resource.setCode("user_manager:manager");
        Set<Resource> resources = Sets.newHashSet(resource);
        Assert.assertFalse(authenticationService.isMatch(new SecurityConfig("user_manager:view"), resources));
    }

}
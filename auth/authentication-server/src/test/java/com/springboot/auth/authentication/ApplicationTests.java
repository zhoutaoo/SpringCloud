package com.springboot.auth.authentication;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;
import java.util.stream.Collectors;


public class ApplicationTests {

    @Test
    public void testMethod() {
        List<SimpleGrantedAuthority> authorities;
        SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ADMIN");
        SimpleGrantedAuthority user = new SimpleGrantedAuthority("USER");
        authorities = Lists.newArrayList(admin, user);
        authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
    }

    @Test
    public void testMatcher() {
        MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/users/{id}");
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("GET", "/users/1")));
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("GET", "/users/aaa")));
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("GET", "/users")));
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("POST", "/users/1")));
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("PUT", "/users/1")));
        System.out.println(mvcRequestMatcher.matches(new MockHttpServletRequest("DELETE", "/users/1")));
    }

}

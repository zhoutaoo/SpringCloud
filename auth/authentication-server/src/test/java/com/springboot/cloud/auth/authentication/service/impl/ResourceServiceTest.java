package com.springboot.cloud.auth.authentication.service.impl;

import com.springboot.cloud.auth.authentication.provider.ResourceProvider;
import com.springboot.cloud.auth.authentication.rest.HttpServletRequestAuthWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.ConfigAttribute;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ResourceServiceTest {

/*    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap() {
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
    };*/

    @InjectMocks
    private ResourceService resourceService;

    @Mock
    private ResourceProvider resourceProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求不存在method的资源时_那么返回NONEXISTENT_URL() {
        ConfigAttribute attributesByUrl = resourceService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users/1/order", "POST"));
        Assert.assertEquals("NONEXISTENT_URL", attributesByUrl.getAttribute());
    }

    @Test
    @Ignore
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求url存在参数时_那么返回匹配的资源信息() {
        ConfigAttribute attributesByUrl = resourceService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users/1/order", "GET"));
        Assert.assertEquals("NONEXISTENT_URL", attributesByUrl.getAttribute());
    }

    @Test
    @Ignore
    public void testGetConfigAttributesByUrl_假如存在如上资源信息_当请求存在的资源时_那么返回url和method都匹配的资源信息() {
        ConfigAttribute attributesByUrl = resourceService
                .findConfigAttributesByUrl(new HttpServletRequestAuthWrapper(new MockHttpServletRequest(), "/users", "POST"));
        Assert.assertEquals("user_manager:btn_add", attributesByUrl.getAttribute());
    }
}
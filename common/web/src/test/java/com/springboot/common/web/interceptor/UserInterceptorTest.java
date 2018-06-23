package com.springboot.common.web.interceptor;

import com.springboot.cloud.common.core.util.UserContextHolder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class UserInterceptorTest {
    @Test
    public void preHandle_当未设置token_user_那么正常处理下一个handle() throws Exception {
        UserInterceptor userInterceptor = new UserInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        userInterceptor.preHandle(request, response, new Object());
    }

    @Test
    public void preHandle_当设置token的username_那么username可以在线程中拿出来用() throws Exception {
        UserInterceptor userInterceptor = new UserInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("x-client-token-user", "{\"user_name\":\"zhangsan\"}");
        MockHttpServletResponse response = new MockHttpServletResponse();
        userInterceptor.preHandle(request, response, new Object());
        Assert.assertEquals(UserContextHolder.getInstance().getUsername(), "zhangsan");
    }
}
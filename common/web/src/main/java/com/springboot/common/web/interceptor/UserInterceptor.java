package com.springboot.common.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.common.core.util.UserContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户信息拦截器
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userinfoString = request.getHeader("x-client-token-user");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userinfoString, Map.class));

        //TODO 从网关获取并校验
        String token = request.getHeader("x-client-token");

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}

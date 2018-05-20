package com.springboot.auth.authentication.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

@Component
@Slf4j
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    /**
     * 用来标识filter名称，判断是否执行过
     * 自定义filter会被spring自动加入servlet filter中，为防止重复执行，在servlet attribute中设置该值
     */
    private static final String FILTER_APPLIED = "__spring_security_customFilterSecurityInterceptor_filterApplied";

    @Autowired
    FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSourceImpl;

    @Override
    @Resource
    @Qualifier("customAccessDecisionManager")
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        log.debug("setAccessDecisionManager:{}", accessDecisionManager);
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return filterInvocationSecurityMetadataSourceImpl;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //未调用过为null,则去调用，并设置为true代表已调用过
        if (servletRequest.getAttribute(FILTER_APPLIED) == null) {
            servletRequest.setAttribute(FILTER_APPLIED, true);
            invoke(new FilterInvocation(servletRequest, servletResponse, filterChain));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            //执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
        log.debug("filter destroy");
    }
}
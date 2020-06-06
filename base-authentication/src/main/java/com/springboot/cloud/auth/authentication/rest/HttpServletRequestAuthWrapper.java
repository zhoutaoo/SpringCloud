package com.springboot.cloud.auth.authentication.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by zhoutaoo on 2018/5/26.
 */
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {

    private String url;
    private String method;

    /**
     * @param url
     * @param method
     */
    public HttpServletRequestAuthWrapper(HttpServletRequest request, String url, String method) {
        super(request);
        this.url = url;
        this.method = method;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getServletPath() {
        return this.url;
    }
}

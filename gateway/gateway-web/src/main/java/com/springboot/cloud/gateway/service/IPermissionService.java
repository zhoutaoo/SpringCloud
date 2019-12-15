package com.springboot.cloud.gateway.service;

public interface IPermissionService {
    /**
     * @param authentication
     * @param method
     * @param url
     * @return
     */
    boolean permission(String authentication, String url, String method);
}

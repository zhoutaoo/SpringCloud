package com.springboot.auth.authentication.service;

import com.springboot.auth.authentication.entity.Resource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Service
public interface IResourceService {

    /**
     * 根据角色code查询到角色把对应的资源定义
     *
     * @param roleCodes
     * @return
     */
    Set<Resource> queryByRoleCodes(String[] roleCodes);

    /**
     * 动态新增权限
     *
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * 加载权限资源数据
     */
    Map<RequestMatcher, ConfigAttribute> loadResource();

    /**
     * 根据url和method查询到对应的权限信息
     *
     * @param authRequest
     * @return
     */
    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest);
}

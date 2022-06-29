package com.springboot.cloud.auth.authentication.service.impl;

import com.springboot.cloud.auth.authentication.service.IAuthenticationService;
import com.springboot.cloud.sysadmin.facade.dto.GroupDTO;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupService groupService;

    /**
     * @param authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    @Override
    public boolean decide(HttpServletRequest authRequest) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取此url，method访问对应的权限资源信息
        ConfigAttribute urlConfigAttribute = resourceService.findConfigAttributesByUrl(authRequest);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute()))
            log.debug("url未在资源池中找到，拒绝访问");
        //获取此访问用户所有角色拥有的权限资源
        Set<Resource> userResources = findResourcesByUsername(authentication.getName());
        //用户拥有权限资源 与 url要求的资源进行对比
        return isMatch(urlConfigAttribute, userResources);
    }

    @Override
    public boolean dataDecide(PermissionDTO permissionDTO) {
        log.debug("正在访问的权限是:{},groupCode:{}", permissionDTO.toString(), permissionDTO.getGroupCode());
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GroupDTO> groupDTOList = groupService.queryGroupsByUsername(authentication.getName());
        //获取组权限列表
        List<PermissionDTO> groupPermissions = groupDTOList.stream()
                .flatMap(x -> permissionService.queryPermissionsByGroupCode(permissionDTO).stream())
                .collect(Collectors.toList());
        return isContainsPermission(groupPermissions, permissionDTO);
    }


    /**
     * 校验权限是否匹配
     *
     * @param requiredPermission 需要的许可
     * @param groupPermissions   组权限
     * @return boolean
     */
    public boolean isContainsPermission(List<PermissionDTO> groupPermissions, PermissionDTO requiredPermission) {
        //权限的父级继承
        for (PermissionDTO groupPermission : groupPermissions) {
            if(requiredPermission.getResFullPath().startsWith(groupPermission.getResFullPath())) {
                return true;
            }
        }
        return false;
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     *
     * @param urlConfigAttribute
     * @param userResources
     * @return
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<Resource> userResources) {
        return userResources.stream().anyMatch(resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     *
     * @param username
     * @return
     */
    private Set<Resource> findResourcesByUsername(String username) {
        //用户被授予的角色资源
        Set<Resource> resources = resourceService.queryByUsername(username);
        if (log.isDebugEnabled()) {
            log.debug("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", resources.size(), resources);
        }
        return resources;
    }
}

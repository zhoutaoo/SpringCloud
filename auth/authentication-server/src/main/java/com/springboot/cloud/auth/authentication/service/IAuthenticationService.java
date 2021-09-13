package com.springboot.cloud.auth.authentication.service;

import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface IAuthenticationService {
    /**
     * 校验权限
     *
     * @param authRequest
     * @return 是否有权限
     */
    boolean decide(HttpServletRequest authRequest);

    /**
     * 校验数据权限
     *
     * @param groupCode     组织代码
     * @param permissionDTO 许可dto
     * @return 是否有权限
     */
    boolean dataDecide(String groupCode, PermissionDTO permissionDTO);

}

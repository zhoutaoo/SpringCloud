package com.springboot.cloud.auth.authentication.service;

import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;

import java.util.List;

public interface IPermissionService {


    /**
     * 通过组织代码查询权限
     * 通过组织代码查询数据权限
     *
     * @param permissionDTO 许可dto
     * @return {@link List<PermissionDTO>}
     */
    List<PermissionDTO> queryPermissionsByGroupCode(PermissionDTO permissionDTO);


}

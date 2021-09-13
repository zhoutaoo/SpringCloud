package com.springboot.cloud.auth.authentication.service;

import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;

import java.util.List;
import java.util.Set;

public interface IPermissionService {


    /**
     * 通过组织代码查询数据权限
     *
     * @param groupCode 组织代码
     * @return {@link Set <PermissionDTO>}
     */
    List<PermissionDTO> queryPermissionsByGroupCode(String groupCode);


    /**
     * 新增数据权限
     *
     * @param permissionDTO 权限dto
     * @param groupCode     组织代码
     */
    void savePermission(String groupCode,PermissionDTO permissionDTO);

    /**
     * 删除权限
     *
     * @param permissionDTO 权限dto
     * @param groupCode     组织代码
     */
    void removePermission(String groupCode,PermissionDTO permissionDTO);

}

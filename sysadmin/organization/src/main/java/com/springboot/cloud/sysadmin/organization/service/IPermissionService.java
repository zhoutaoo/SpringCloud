package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.entity.po.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询条件
     *
     * @param permissionDTO 许可dto
     * @return {@link List<Permission>}
     */
    List<Permission> queryByConditions(PermissionDTO permissionDTO);

    /**
     * 新增资源
     *
     * @param groupCode  组织代码
     * @param permission 许可
     * @return boolean
     */
    boolean add(String groupCode, Permission permission);


    /**
     * 更新
     * 更新资源信息
     *
     * @param permission 许可
     * @param groupCode  组织代码
     * @return boolean
     */
    boolean update(String groupCode, Permission permission);

    /**
     * 删除
     * 根据id删除资源
     *
     * @param groupCode  组织代码
     * @param permission 许可
     * @return boolean
     */
    boolean delete(String groupCode, Permission permission);

}

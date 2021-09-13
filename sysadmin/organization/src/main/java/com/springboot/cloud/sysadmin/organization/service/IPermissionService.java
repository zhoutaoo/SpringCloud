package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.po.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询组
     * 由集团代码查询权限
     *
     * @param groupList 组列表
     * @return {@link List<Permission>}
     */
    List<Permission> queryByGroups(List<String> groupList);

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

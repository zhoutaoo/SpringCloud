package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.po.RoleResource;

import java.util.List;
import java.util.Set;

public interface IRoleResourceService {

    /**
     * 批量给角色添加资源
     *
     * @param roleId      角色id
     * @param resourceIds 资源id列表
     * @return 是否操作成功
     */
    boolean saveBatch(String roleId, Set<String> resourceIds);

    /**
     * 删除角色拥有的资源
     *
     * @param roleId 角色id
     * @return 是否操作成功
     */
    boolean removeByRoleId(String roleId);

    /**
     * 查询角色拥有资源id
     *
     * @param roleId 角色id
     * @return 角色拥有的资源id集合
     */
    Set<String> queryByRoleId(String roleId);

    /**
     * 根据角色id列表查询资源关系
     *
     * @param roleIds 角色id集合
     * @return 角色资源关系集合
     */
    List<RoleResource> queryByRoleIds(Set<String> roleIds);
}

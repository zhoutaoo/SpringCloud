package com.springboot.cloud.sysadmin.organization.service;

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
     * 批量给角色添加资源
     *
     * @param roleId      角色id
     * @param resourceIds 资源id列表
     * @return 是否操作成功
     */
    boolean saveOrUpdateBatch(String roleId, Set<String> resourceIds);

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
}

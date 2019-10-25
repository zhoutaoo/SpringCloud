package com.springboot.cloud.sysadmin.organization.service;

import java.util.Set;

public interface IUserRoleService {

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveBatch(String userId, Set<String> roleIds);

    /**
     * 删除用户拥有的角色
     *
     * @param userId
     * @return
     */
    boolean removeByUserId(String userId);

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId
     * @return
     */
    Set<String> queryByUserId(String userId);
}

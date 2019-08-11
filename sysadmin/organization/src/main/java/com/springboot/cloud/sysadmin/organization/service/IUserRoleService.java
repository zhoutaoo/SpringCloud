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
    boolean saveBatch(long userId, Set<Long> roleIds);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveOrUpdateBatch(long userId, Set<Long> roleIds);
}

package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.param.RoleQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    Role get(long id);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    long add(Role role);

    /**
     * 查询角色
     *
     * @return
     */
    List<Role> query(RoleQueryParam roleQueryParam);

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @return
     */
    List<Role> query(long userId);

    /**
     * 更新角色信息
     *
     * @param role
     */
    void update(Role role);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    void delete(long id);
}

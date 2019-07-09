package com.springboot.cloud.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.sysadmin.organization.entity.param.UserQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.User;

public interface IUserService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User get(long id);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    long add(User user);

    /**
     * 查询用户
     *
     * @return
     */
    IPage<User> query(Page<User> page, UserQueryParam userQueryParam);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delete(long id);

}

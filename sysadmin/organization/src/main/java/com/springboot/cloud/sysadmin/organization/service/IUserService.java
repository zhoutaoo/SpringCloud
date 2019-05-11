package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.param.UserQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.User;

import java.util.List;

public interface IUserService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User get(long id);

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
    List<User> query(UserQueryParam userQueryParam);

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

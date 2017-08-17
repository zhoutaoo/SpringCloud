package com.springboot.oauth2.service;

import com.springboot.oauth2.entity.User;

public interface IUserService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User get(long id);

    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    User loadByUsername(String username);
}

package com.springboot.auth.authorization.service.impl;

import com.springboot.auth.authorization.dao.UserMapper;
import com.springboot.auth.authorization.entity.User;
import com.springboot.auth.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}

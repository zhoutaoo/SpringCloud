package com.springboot.oauth2.service.impl;

import com.springboot.oauth2.dao.UserMapper;
import com.springboot.oauth2.entity.User;
import com.springboot.oauth2.service.IUserService;
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

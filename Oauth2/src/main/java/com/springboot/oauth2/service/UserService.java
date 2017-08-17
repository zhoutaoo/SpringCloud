package com.springboot.oauth2.service;

import com.springboot.oauth2.dao.UserMapper;
import com.springboot.oauth2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(long id) {
        return userMapper.get(id);
    }

    @Override
    public User loadByUsername(String username) {
        return userMapper.loadByUsername(username);
    }
}

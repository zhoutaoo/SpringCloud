package com.springboot.producer.service;

import com.springboot.producer.dao.UserMapper;
import com.springboot.producer.entity.User;
import com.springboot.producer.entity.param.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(long id) {
        return userMapper.select(id);
    }

    @Override
    public long add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> query(UserQueryParam userQueryParam) {
        return userMapper.query(userQueryParam);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(long id) {
        userMapper.delete(id);
    }
}

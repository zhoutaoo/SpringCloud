package com.springboot.oauth2.service;

import com.springboot.oauth2.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    @Cacheable(value = "#id")
    User getByUsername(String username);
}

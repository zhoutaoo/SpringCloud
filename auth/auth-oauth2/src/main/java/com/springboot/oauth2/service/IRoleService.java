package com.springboot.oauth2.service;

import com.springboot.oauth2.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IRoleService {

    Set<Role> findByUserId(long userId);
}

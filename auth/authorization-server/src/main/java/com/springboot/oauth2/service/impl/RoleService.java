package com.springboot.oauth2.service.impl;

import com.springboot.oauth2.dao.RoleMapper;
import com.springboot.oauth2.entity.Role;
import com.springboot.oauth2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> queryUserRolesByUserId(long userId) {
        return roleMapper.queryByUserId(userId);
    }

}

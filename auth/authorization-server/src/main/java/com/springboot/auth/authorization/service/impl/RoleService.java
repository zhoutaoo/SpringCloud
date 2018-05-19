package com.springboot.auth.authorization.service.impl;

import com.springboot.auth.authorization.dao.RoleMapper;
import com.springboot.auth.authorization.entity.Role;
import com.springboot.auth.authorization.service.IRoleService;
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

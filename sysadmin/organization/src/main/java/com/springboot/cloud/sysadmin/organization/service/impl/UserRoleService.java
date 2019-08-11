package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.sysadmin.organization.dao.UserRoleMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.UserRole;
import com.springboot.cloud.sysadmin.organization.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public boolean saveBatch(long userId, Set<Long> roleIds) {
        Set<UserRole> userRoles = roleIds.stream().map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    public boolean saveOrUpdateBatch(long userId, Set<Long> roleIds) {
        Set<UserRole> userRoles = roleIds.stream().map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toSet());
        return saveOrUpdateBatch(userRoles);
    }
}

package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.sysadmin.organization.dao.RoleMapper;
import com.springboot.cloud.sysadmin.organization.dao.UserRoleMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.RoleQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import com.springboot.cloud.sysadmin.organization.entity.po.UserRole;
import com.springboot.cloud.sysadmin.organization.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public long add(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#id")
    public void delete(long id) {
        roleMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#role.id")
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    @Cacheable(value = "role", key = "#root.targetClass.name+'-'+#id")
    public Role get(long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> query(long userId) {
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
        return roleMapper.selectBatchIds(userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList()));
    }

    @Override
    public List<Role> query(RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != roleQueryParam.getName(), "name", roleQueryParam.getName());
        queryWrapper.eq(null != roleQueryParam.getCode(), "code", roleQueryParam.getCode());
        return roleMapper.selectList(queryWrapper);
    }

}

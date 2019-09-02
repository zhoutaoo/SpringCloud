package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.sysadmin.organization.dao.RoleMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.RoleQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import com.springboot.cloud.sysadmin.organization.service.IRoleService;
import com.springboot.cloud.sysadmin.organization.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public boolean add(Role role) {
        return this.save(role);
    }

    @Override
    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#id")
    public boolean delete(String id) {
        return this.delete(id);
    }

    @Override
    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#role.id")
    public boolean update(Role role) {
        return this.updateById(role);
    }

    @Override
    @Cacheable(value = "role", key = "#root.targetClass.name+'-'+#id")
    public Role get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Role> get() {
        return this.list();
    }

    @Override
    @Cacheable(value = "role4user", key = "#root.targetClass.name+'-'+#userId")
    public List<Role> query(String userId) {
        Set<String> roleIds = userRoleService.queryByUserId(userId);
        return (List<Role>) this.listByIds(roleIds);
    }

    @Override
    public IPage<Role> query(Page page, RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> queryWrapper = roleQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode());
        return this.page(page, queryWrapper);
    }
}

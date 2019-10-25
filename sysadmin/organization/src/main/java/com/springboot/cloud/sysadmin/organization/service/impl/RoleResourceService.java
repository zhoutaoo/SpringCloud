package com.springboot.cloud.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.sysadmin.organization.dao.RoleResourceMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.RoleResource;
import com.springboot.cloud.sysadmin.organization.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleResourceService extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    @Transactional
    public boolean saveBatch(String roleId, Set<String> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds))
            return false;
        removeByRoleId(roleId);
        Set<RoleResource> userRoles = resourceIds.stream().map(resourceId -> new RoleResource(roleId, resourceId)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    @Transactional
    public boolean removeByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        return remove(queryWrapper);
    }

    @Override
    @Cached(area = "shortTime", name = "resource4role::", key = "#roleId", cacheType = CacheType.BOTH)
    public Set<String> queryByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleResource> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<String> roleIds) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }
}

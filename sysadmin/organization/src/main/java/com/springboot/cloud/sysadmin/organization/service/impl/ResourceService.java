package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.sysadmin.organization.config.BusConfig;
import com.springboot.cloud.sysadmin.organization.dao.ResourceMapper;
import com.springboot.cloud.sysadmin.organization.dao.RoleResourceMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.ResourceQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import com.springboot.cloud.sysadmin.organization.entity.po.RoleResource;
import com.springboot.cloud.sysadmin.organization.entity.po.User;
import com.springboot.cloud.sysadmin.organization.events.EventSender;
import com.springboot.cloud.sysadmin.organization.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventSender eventSender;

    @Override
    public long add(Resource resource) {
        eventSender.send(BusConfig.QUEUE_NAME, resource);
        return resourceMapper.insert(resource);
    }

    @Override
    @CacheEvict(value = "resource", key = "#root.targetClass.name+'-'+#id")
    public void delete(String id) {
        resourceMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "resource", key = "#root.targetClass.name+'-'+#resource.id")
    public void update(Resource resource) {
        resourceMapper.updateById(resource);
    }

    @Override
    @Cacheable(value = "resource", key = "#root.targetClass.name+'-'+#id")
    public Resource get(String id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public List<Resource> query(ResourceQueryParam resourceQueryParam) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != resourceQueryParam.getCreatedTimeStart(), "created_time", resourceQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != resourceQueryParam.getCreatedTimeEnd(), "created_time", resourceQueryParam.getCreatedTimeEnd());
        queryWrapper.eq(null != resourceQueryParam.getName(), "name", resourceQueryParam.getName());
        queryWrapper.eq(null != resourceQueryParam.getType(), "type", resourceQueryParam.getType());
        queryWrapper.eq(null != resourceQueryParam.getUrl(), "url", resourceQueryParam.getUrl());
        queryWrapper.eq(null != resourceQueryParam.getMethod(), "method", resourceQueryParam.getMethod());
        return resourceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Resource> query(String username) {
        //根据用户名查询到用户所拥有的角色
        User user = userService.getByUniqueId(username);
        List<Role> roles = roleService.query(user.getId());
        //提取用户所拥有角色的id列表
        List<String> roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        //根据角色列表查询到角色的资源的关联
        List<RoleResource> roleResources = roleResourceMapper.selectList(new QueryWrapper<RoleResource>().in("role_id", roleIds));
        //根据资源列表查询出所有资源对象
        return resourceMapper.selectBatchIds(roleResources.stream().map(roleResource -> roleResource.getResourceId()).collect(Collectors.toList()));
    }

}

package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.sysadmin.organization.dao.ResourceMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.ResourceQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import com.springboot.cloud.sysadmin.organization.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public long add(Resource resource) {
        return resourceMapper.insert(resource);
    }

    @Override
    @CacheEvict(value = "resource", key = "#root.targetClass.name+'-'+#id")
    public void delete(long id) {
        resourceMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "resource", key = "#root.targetClass.name+'-'+#resource.id")
    public void update(Resource resource) {
        resourceMapper.updateById(resource);
    }

    @Override
    @Cacheable(value = "resource", key = "#root.targetClass.name+'-'+#id")
    public Resource get(long id) {
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

}

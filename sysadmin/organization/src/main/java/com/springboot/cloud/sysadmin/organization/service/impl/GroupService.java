package com.springboot.cloud.sysadmin.organization.service.impl;

import com.springboot.cloud.sysadmin.organization.dao.GroupMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.GroupQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Group;
import com.springboot.cloud.sysadmin.organization.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupService implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public long add(Group group) {
        return groupMapper.insert(group);
    }

    @Override
    @CacheEvict(value = "group", key = "#root.targetClass.name+'-'+#id")
    public void delete(long id) {
        groupMapper.delete(id);
    }

    @Override
    @CacheEvict(value = "group", key = "#root.targetClass.name+'-'+#group.id")
    public void update(Group group) {
        groupMapper.update(group);
    }

    @Override
    @Cacheable(value = "group", key = "#root.targetClass.name+'-'+#id")
    public Group get(long id) {
        return groupMapper.select(id);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        return groupMapper.query(groupQueryParam);
    }

    @Override
    public List<Group> queryByParentId(long id) {
        return groupMapper.queryByParentId(id);
    }
}

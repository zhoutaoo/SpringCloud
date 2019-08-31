package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public void delete(String id) {
        groupMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "group", key = "#root.targetClass.name+'-'+#group.id")
    public void update(Group group) {
        groupMapper.updateById(group);
    }

    @Override
    @Cacheable(value = "group", key = "#root.targetClass.name+'-'+#id")
    public Group get(String id) {
        return groupMapper.selectById(id);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != groupQueryParam.getCreatedTimeStart(), "created_time", groupQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != groupQueryParam.getCreatedTimeEnd(), "created_time", groupQueryParam.getCreatedTimeEnd());
        queryWrapper.eq("name", groupQueryParam.getName());
        return groupMapper.selectList(queryWrapper);
    }

    @Override
    public List<Group> queryByParentId(String id) {
        return groupMapper.selectList(new QueryWrapper<Group>().eq("parent_id", id));
    }
}

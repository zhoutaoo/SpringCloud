package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.sysadmin.organization.dao.PositionMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.PositionQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Position;
import com.springboot.cloud.sysadmin.organization.service.IPositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionService implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public long add(Position position) {
        return positionMapper.insert(position);
    }

    @Override
    @CacheEvict(value = "position", key = "#root.targetClass.name+'-'+#id")
    public void delete(String id) {
        positionMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "position", key = "#root.targetClass.name+'-'+#position.id")
    public void update(Position position) {
        positionMapper.updateById(position);
    }

    @Override
    @Cacheable(value = "position", key = "#root.targetClass.name+'-'+#id")
    public Position get(String id) {
        return positionMapper.selectById(id);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != positionQueryParam.getName(), "name", positionQueryParam.getName());
        return positionMapper.selectList(queryWrapper);
    }

}

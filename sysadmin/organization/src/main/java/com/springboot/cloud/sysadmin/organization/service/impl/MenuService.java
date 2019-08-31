package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.sysadmin.organization.dao.MenuMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.MenuQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Menu;
import com.springboot.cloud.sysadmin.organization.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public long add(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    @CacheEvict(value = "menu", key = "#root.targetClass.name+'-'+#id")
    public void delete(String id) {
        menuMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "menu", key = "#root.targetClass.name+'-'+#menu.id")
    public void update(Menu menu) {
        menuMapper.updateById(menu);
    }

    @Override
    @Cacheable(value = "menu", key = "#root.targetClass.name+'-'+#id")
    public Menu get(String id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(), "name", menuQueryParam.getName());
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(long id) {
        return menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", id));
    }
}

package com.springboot.cloud.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.sysadmin.organization.dao.MenuMapper;
import com.springboot.cloud.sysadmin.organization.entity.param.MenuQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Menu;
import com.springboot.cloud.sysadmin.organization.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public boolean add(Menu menu) {
        return this.save(menu);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
    @Cached(name = "menu::", key = "#id", cacheType = CacheType.BOTH)
    public Menu get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(), "name", menuQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(String id) {
        return this.list(new QueryWrapper<Menu>().eq("parent_id", id));
    }
}

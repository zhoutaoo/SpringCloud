package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.param.MenuQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Menu;

import java.util.List;

public interface IMenuService {
    /**
     * 获取菜单
     *
     * @param id
     * @return
     */
    Menu get(String id);

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    long add(Menu menu);

    /**
     * 查询菜单
     *
     * @return
     */
    List<Menu> query(MenuQueryParam menuQueryParam);

    /**
     * 根据父id查询菜单
     *
     * @return
     */
    List<Menu> queryByParentId(long id);

    /**
     * 更新菜单信息
     *
     * @param menu
     */
    void update(Menu menu);

    /**
     * 根据id删除菜单
     *
     * @param id
     */
    void delete(String id);
}

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
    boolean add(Menu menu);

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
    List<Menu> queryByParentId(String id);

    /**
     * 更新菜单信息
     *
     * @param menu
     */
    boolean update(Menu menu);

    /**
     * 根据id删除菜单
     *
     * @param id
     */
    boolean delete(String id);
}

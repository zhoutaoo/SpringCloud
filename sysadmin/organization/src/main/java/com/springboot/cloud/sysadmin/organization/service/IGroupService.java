package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.sysadmin.organization.entity.param.GroupQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Group;

import java.util.List;

public interface IGroupService {
    /**
     * 获取用户组
     *
     * @param id
     * @return
     */
    Group get(long id);

    /**
     * 新增用户组
     *
     * @param group
     * @return
     */
    long add(Group group);

    /**
     * 查询用户组
     *
     * @return
     */
    List<Group> query(GroupQueryParam groupQueryParam);

    /**
     * 根据父id查询用户组
     *
     * @return
     */
    List<Group> queryByParentId(long id);

    /**
     * 更新用户组信息
     *
     * @param group
     */
    void update(Group group);

    /**
     * 根据id删除用户组
     *
     * @param id
     */
    void delete(long id);
}

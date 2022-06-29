package com.springboot.cloud.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.GroupPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 组权限映射器
 *
 * @author wayne
 * @date 2021/09/09
 */
@Repository
@Mapper
public interface GroupPermissionMapper extends BaseMapper<GroupPermission> {
}

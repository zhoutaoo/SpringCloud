package com.springboot.cloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.dao.GroupMapper;
import com.springboot.cloud.sysadmin.organization.dao.GroupPermissionMapper;
import com.springboot.cloud.sysadmin.organization.dao.PermissionMapper;
import com.springboot.cloud.sysadmin.organization.dao.UserGroupMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.Group;
import com.springboot.cloud.sysadmin.organization.entity.po.GroupPermission;
import com.springboot.cloud.sysadmin.organization.entity.po.Permission;
import com.springboot.cloud.sysadmin.organization.events.EventSender;
import com.springboot.cloud.sysadmin.organization.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    EventSender eventSender;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Permission> queryByConditions(PermissionDTO permissionDTO) {
        //get permission Id by groupIds
        LambdaQueryWrapper<GroupPermission> groupPermissionQueryWrapper = new LambdaQueryWrapper<>();
        groupPermissionQueryWrapper.in(GroupPermission::getGroupId,permissionDTO.getGroupCode());
        List<GroupPermission> groupPermissionList = groupPermissionMapper.selectList(groupPermissionQueryWrapper);

        //get permissions
        List<String> permissionIdList = groupPermissionList.stream().map(GroupPermission::getPermissionId).collect(Collectors.toList());
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        permissionLambdaQueryWrapper.in(Permission::getId,permissionIdList)
                                    .eq(Permission::getArea,permissionDTO.getArea())
                                    .eq(Permission::getOperationBit,permissionDTO.getOperationBit())
                                    .eq(Permission::getResType,permissionDTO.getResType())
                                    .eq(Permission::getDeleted,"N")
                                    .gt(Permission::getExpireDate,new Date());
        return permissionMapper.selectList(permissionLambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(String groupCode, Permission permission) {
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getName,groupCode);
        Group group = groupMapper.selectOne(groupLambdaQueryWrapper);
        GroupPermission groupPermission = GroupPermission.builder().groupId(group.getId()).permissionId(permission.getId()).build();
        //todo permission的格式校验
        int insertGroupNum = groupPermissionMapper.insert(groupPermission);
        int insertPermissionNum = permissionMapper.insert(permission);
        return isInsertSuccess(insertGroupNum,insertPermissionNum);
    }

    @Override
    public boolean update(String groupCode, Permission permission) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String groupCode, Permission permission) {
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getName,groupCode);
        Group group = groupMapper.selectOne(groupLambdaQueryWrapper);

        //delete groupPermission
        LambdaQueryWrapper<GroupPermission> groupPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        groupPermissionLambdaQueryWrapper
                .eq(GroupPermission::getGroupId,group.getId())
                .eq(GroupPermission::getPermissionId,permission.getId());
        groupPermissionMapper.delete(groupPermissionLambdaQueryWrapper);

        //delete permission
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        permissionLambdaQueryWrapper
                .eq(Permission::getId,permission.getId());
        permissionMapper.delete(permissionLambdaQueryWrapper);
        return true;
    }

    /**
     * 校验插入是否成功
     *
     * @param insertNum1 插入num1
     * @param insertNum2 插入num2
     * @return boolean
     */
    public boolean isInsertSuccess(Integer insertNum1, Integer insertNum2){
         return (null != insertNum1 && insertNum1 >= 1) && (null != insertNum2 && insertNum2 >= 1);
    }
}

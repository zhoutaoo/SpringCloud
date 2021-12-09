package com.springboot.cloud.sysadmin.organization.events;


import com.springboot.cloud.sysadmin.facade.constant.PermissionChangeTypeEnum;
import com.springboot.cloud.sysadmin.facade.dto.PermissionChangeDTO;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.dao.GroupPermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限更新总线接收器
 *
 * @author wayne
 * @date 2021/09/08
 */
@Component
@Slf4j
public class PermissionBusReceiver {

    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    public void handleMessage(PermissionChangeDTO permissionChangeDTO) {
        log.info("Received Message:<{}>", permissionChangeDTO);
        PermissionChangeTypeEnum changeType = permissionChangeDTO.getChangeType();
        PermissionDTO permissionDTO = permissionChangeDTO.getPermissionDTO();
        if(changeType.equals(PermissionChangeTypeEnum.DELETE)){
            removePermission(permissionDTO);
        }else if(changeType.equals(PermissionChangeTypeEnum.ADD)){
            savePermission(permissionDTO);
        }else{
            log.info("错误的消息,被舍弃");
        }
    }


    public void savePermission(PermissionDTO permissionDTO) {
        //1.检查权限格式是否正确
        //2.添加权限表
        //3.添加权限
    }

    public void removePermission(PermissionDTO permissionDTO) {
        //去除group_permission表中的权限关联
    }
}

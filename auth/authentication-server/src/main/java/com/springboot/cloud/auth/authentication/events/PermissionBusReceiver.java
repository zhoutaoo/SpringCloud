package com.springboot.cloud.auth.authentication.events;


import com.springboot.cloud.auth.authentication.service.impl.PermissionService;
import com.springboot.cloud.sysadmin.facade.constant.PermissionChangeTypeEnum;
import com.springboot.cloud.sysadmin.facade.dto.PermissionChangeDTO;
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
    private PermissionService permissionService;

    public void handleMessage(PermissionChangeDTO permissionChangeDTO) {
        log.info("Received Message:<{}>", permissionChangeDTO);
        PermissionChangeTypeEnum changeType = permissionChangeDTO.getChangeType();
        if(changeType.equals(PermissionChangeTypeEnum.DELETE)){
            permissionService.removePermission(permissionChangeDTO.getGroupCode(), permissionChangeDTO.getPermissionDTO());
        }else if(changeType.equals(PermissionChangeTypeEnum.ADD)){
            permissionService.savePermission(permissionChangeDTO.getGroupCode(), permissionChangeDTO.getPermissionDTO());
        }else{
            log.info("错误的消息,被舍弃");
        }
    }
}

package com.springboot.cloud.sysadmin.facade.dto;

import com.springboot.cloud.sysadmin.facade.constant.PermissionChangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限变动通知MQ
 *
 * @author wayne
 * @date 2021/09/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionChangeDTO implements Serializable {
    private PermissionChangeTypeEnum changeType;
    private String groupCode;
    private PermissionDTO permissionDTO;
}

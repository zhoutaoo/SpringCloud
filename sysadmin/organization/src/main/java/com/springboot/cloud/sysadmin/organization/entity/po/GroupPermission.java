package com.springboot.cloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组权限
 *
 * @author wayne
 * @date 2021/09/09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("group_permission_relation")
public class GroupPermission extends BasePo {
    private String groupId;
    private String permissionId;
}

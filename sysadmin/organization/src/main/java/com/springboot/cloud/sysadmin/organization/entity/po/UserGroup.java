package com.springboot.cloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户组
 *
 * @author wayne
 * @date 2021/09/09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_group_relation")
public class UserGroup extends BasePo {
    private String userId;
    private String groupId;
}

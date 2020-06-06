package com.springboot.cloud.sysadmin.organization.entity.param;

import com.springboot.cloud.common.web.entity.param.BaseParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryParam extends BaseParam<Role> {
    private String code;
    private String name;
}

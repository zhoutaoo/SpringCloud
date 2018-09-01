package com.springboot.cloud.sysadmin.organization.entity.po;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BasePo {
    private String code;
    private String name;
    private String description;
}

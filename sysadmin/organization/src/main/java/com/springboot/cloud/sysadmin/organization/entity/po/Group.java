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
public class Group extends BasePo {
    private String name;
    private long parentId;
    private String description;
}

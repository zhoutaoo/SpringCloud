package com.springboot.auth.authorization.entity;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role extends BasePo {
    private String code;
    private String name;
    private String description;
}

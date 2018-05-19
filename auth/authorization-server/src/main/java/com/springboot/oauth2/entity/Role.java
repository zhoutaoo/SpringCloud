package com.springboot.oauth2.entity;

import com.springboot.cloud.core.entity.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role extends Entity {
    private String code;
    private String name;
    private String description;
}

package com.springboot.oauth2.entity;

import com.springboot.cloud.core.entity.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resource extends Entity {
    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
}

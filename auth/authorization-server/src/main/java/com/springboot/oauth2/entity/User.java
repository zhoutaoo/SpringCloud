package com.springboot.oauth2.entity;

import com.springboot.cloud.core.entity.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends Entity {
    private String name;
    private String mobile;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
}
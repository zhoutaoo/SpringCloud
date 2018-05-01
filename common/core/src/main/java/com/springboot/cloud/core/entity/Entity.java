package com.springboot.cloud.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Entity implements Serializable {
    private Long id;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}

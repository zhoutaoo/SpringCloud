package com.springboot.cloud.common.core.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class BasePo {
    private Long id;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}

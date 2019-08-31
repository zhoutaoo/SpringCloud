package com.springboot.cloud.demos.producer.jpa.entity.po;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@MappedSuperclass
public class JpaBasePo implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    private String createdBy = DEFAULT_USERNAME;
    private String updatedBy = DEFAULT_USERNAME;
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
    private Date updatedTime = Date.from(ZonedDateTime.now().toInstant());
}

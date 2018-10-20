package com.springboot.cloud.demos.producer.jpa.entity.po;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class JpaBasePo extends BasePo {
    private String createdBy = super.getCreatedBy();
    private String updatedBy = super.getUpdatedBy();
    private Date createdTime = super.getCreatedTime();
    private Date updatedTime = super.getUpdatedTime();
}

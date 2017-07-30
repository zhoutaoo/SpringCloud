package com.springboot.producer.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
public class UserUpdateForm {
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @Past(message = "查询日期必须小于当前日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

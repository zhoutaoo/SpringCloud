package com.springboot.producer.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
public class UserQueryForm {
    @ApiModelProperty(value = "用户姓名", required = true)
    @NotEmpty(message = "姓名不能为空")
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

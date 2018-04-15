package com.springboot.producer.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
@Data
public class ProductQueryForm {
    @ApiModelProperty(value = "产品名称", required = true)
    @NotEmpty(message = "名称不能为空")
    private String name;

    @Past(message = "查询日期必须小于当前日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdDate;
}

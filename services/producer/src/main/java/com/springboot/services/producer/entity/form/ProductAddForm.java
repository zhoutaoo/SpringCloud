package com.springboot.services.producer.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class ProductAddForm {

    @NotBlank(message = "产品名称不能为空")
    @ApiModelProperty(value = "产品名称", required = true)
    private String name;

    @ApiModelProperty(value = "产品描述")
    private String description;
}

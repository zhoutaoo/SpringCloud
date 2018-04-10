package com.springboot.producer.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ProductUpdateForm {
    @ApiModelProperty(value = "产品名称")
    private String name;
}

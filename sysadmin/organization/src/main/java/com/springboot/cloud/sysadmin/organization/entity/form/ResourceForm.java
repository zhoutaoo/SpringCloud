package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class ResourceForm extends BaseForm<Resource> {

    @NotBlank(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称")
    private String name;

    @NotBlank(message = "资源编码不能为空")
    @ApiModelProperty(value = "资源编码")
    private String code;

    @NotBlank(message = "资源类型不能为空")
    @ApiModelProperty(value = "资源类型")
    private String type;

    @NotBlank(message = "资源路径不能为空")
    @ApiModelProperty(value = "资源路径")
    private String url;

    @NotBlank(message = "资源方法不能为空")
    @ApiModelProperty(value = "资源方法")
    private String method;

    @ApiModelProperty(value = "资源描述")
    private String description;
}

package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.springboot.cloud.sysadmin.organization.entity.po.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class MenuForm extends BaseForm<Menu> {

    @NotBlank(message = "菜单父id不能为空")
    @ApiModelProperty(value = "菜单父id")
    private String parentId;

    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @NotBlank(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单类型")
    private String type;

    @NotBlank(message = "菜单路径不能为空")
    @ApiModelProperty(value = "菜单路径")
    private String href;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单序号")
    private String orderNum;

    @ApiModelProperty(value = "菜单描述")
    private String description;
}

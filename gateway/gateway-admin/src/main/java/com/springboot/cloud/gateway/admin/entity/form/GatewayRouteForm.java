package com.springboot.cloud.gateway.admin.entity.form;

import com.springboot.cloud.common.core.entity.form.BaseForm;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class GatewayRouteForm extends BaseForm<GatewayRoute> {

    @NotBlank(message = "网关断言不能为空")
    @ApiModelProperty(value = "网关断言")
    private String predicates;

    @ApiModelProperty(value = "网关过滤器信息")
    private String filters;

    @ApiModelProperty(value = "网关uri")
    private String uri;

    @ApiModelProperty(value = "网关路由描述信息")
    private String description;
}

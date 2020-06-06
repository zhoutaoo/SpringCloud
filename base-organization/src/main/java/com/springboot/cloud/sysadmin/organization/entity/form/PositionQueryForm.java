package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseQueryForm;
import com.springboot.cloud.sysadmin.organization.entity.param.ResourceQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
@Data
public class PositionQueryForm extends BaseQueryForm<ResourceQueryParam> {

    @NotBlank(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称", required = true)
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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}

package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.springboot.cloud.sysadmin.organization.entity.po.Permission;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 数据权限DTO
 *
 * @author wayne
 * @date 2021/09/06
 */
public class PermissionForm extends BaseForm<Permission> {
    /**
     * 资源类型：hive，hdfs
     */
    @NotBlank(message = "资源类型不能为空")
    @ApiModelProperty(value = "资源类型")
    private String resType;

    /**
     * 资源地区
     */
    @NotBlank(message = "资源地区不能为空")
    @ApiModelProperty(value = "资源地区")
    private String area;

    /**
     * 资源完整路径
     */
    @NotBlank(message = "资源路径不能为空")
    @ApiModelProperty(value = "资源路径")
    private String resFullPath;

    /**
     * 资源全名
     */
    @NotBlank(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称")
    private String resFullName;

    /**
     * 资源操作位：读，写，查询
     */
    @NotBlank(message = "资源操作位不能为空")
    @ApiModelProperty(value = "资源操作位")
    private String operationBit;
}

package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.springboot.cloud.sysadmin.organization.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@ApiModel
@Data
public class UserUpdateForm extends BaseForm<User> {

    @ApiModelProperty(value = "用户账号")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户描述")
    private String description;

    @ApiModelProperty(value = "用户拥有的角色id列表")
    private Set<String> roleIds;

    @ApiModelProperty(value = "用户状态，true为可用")
    private Boolean enabled = true;

    @ApiModelProperty(value = "用户账号是否过期，true为未过期")
    private Boolean accountNonExpired = true;

    @ApiModelProperty(value = "用户密码是否过期，true为未过期")
    private Boolean credentialsNonExpired = true;

    @ApiModelProperty(value = "用户账号是否被锁定，true为未锁定")
    private Boolean accountNonLocked = true;
}

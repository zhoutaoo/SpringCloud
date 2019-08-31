package com.springboot.cloud.sysadmin.organization.entity.param;

import com.springboot.cloud.common.web.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam extends BaseParam {
    private String name;
    private String mobile;
    private String username;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}

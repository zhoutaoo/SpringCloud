package com.springboot.cloud.gateway.admin.entity.param;

import com.springboot.cloud.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam {
    private String uri;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}

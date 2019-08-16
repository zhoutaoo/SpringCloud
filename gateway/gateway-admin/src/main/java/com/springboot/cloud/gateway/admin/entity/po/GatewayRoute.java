package com.springboot.cloud.gateway.admin.entity.po;

import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BasePo {
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders = 0;
    private String status = "Y";
}

package com.springboot.cloud.gateway.admin.entity.po;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BasePo {
    private String uri;
    private String predicates;
    private String filters;
    private String description;
    private String status;
}

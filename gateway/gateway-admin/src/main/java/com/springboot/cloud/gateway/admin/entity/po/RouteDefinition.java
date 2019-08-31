package com.springboot.cloud.gateway.admin.entity.po;

import com.springboot.cloud.common.web.entity.po.BasePo;
import com.sun.jndi.toolkit.url.Uri;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDefinition extends BasePo {
    private Uri uri;
    private String routeId;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();
    private String description;
    private Integer orders;
    private String status;
}

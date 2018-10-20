package com.springboot.cloud.demos.producer.entity.param;

import com.springboot.cloud.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryParam extends BaseParam {
    private String name;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}

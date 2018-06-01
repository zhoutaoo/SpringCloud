package com.springboot.services.producer.entity.po;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Product extends BasePo {
    private String name;
    private String description;

    public Product(@NotBlank(message = "产品名称不能为空") String name, String description) {
        this.name = name;
        this.description = description;
    }
}

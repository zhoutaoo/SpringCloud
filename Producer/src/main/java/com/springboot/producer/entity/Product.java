package com.springboot.producer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Product implements Serializable{
    private Long id;
    private String name;
    private String description;
    private Date updatedTime;
    private Date createdTime;
    private String updatedBy;
    private String createdBy;

    public Product(@NotBlank(message = "产品名称不能为空") String name, String description) {
        this.name = name;
        this.description = description;
    }
}

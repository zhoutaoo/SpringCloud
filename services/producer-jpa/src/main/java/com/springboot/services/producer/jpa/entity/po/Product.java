package com.springboot.services.producer.jpa.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends JpaBasePo {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}

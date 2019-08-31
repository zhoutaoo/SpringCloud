package com.springboot.cloud.demos.producer.jpa.entity.po;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends JpaBasePo {

    @Id
    @GeneratedValue(generator = "productGenerator")
    @GenericGenerator(name = "productGenerator", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}

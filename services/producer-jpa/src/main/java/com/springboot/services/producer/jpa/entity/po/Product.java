package com.springboot.services.producer.jpa.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends JpaBasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = UUID.randomUUID().getLeastSignificantBits();

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}

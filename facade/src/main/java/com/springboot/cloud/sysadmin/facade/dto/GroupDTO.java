package com.springboot.cloud.sysadmin.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 集团dto
 * 组DTO
 *
 * @author wayne
 * @date 2021/09/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO implements Serializable {

    private String name;
    private String parentId;
    private String description;
}

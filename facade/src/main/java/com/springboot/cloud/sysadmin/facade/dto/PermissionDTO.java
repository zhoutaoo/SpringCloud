package com.springboot.cloud.sysadmin.facade.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据权限资源传输类
 *
 * @author wayne
 * @date 2021/09/06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO implements Serializable {


    /**
     * 组织代码
     */
    private String groupCode;
    /**
     * 资源类型：hive，hdfs
     */
    private String resType;

    /**
     * 资源地区
     */
    private String area;

    /**
     * 资源完整路径
     */
    private String resFullPath;

    /**
     * 资源全名
     */
    private String resFullName;

    /**
     * 资源操作位：读，写，查询
     */
    private String operationBit;
}

package com.springboot.cloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.*;

import java.util.Date;

/**
 * 许可
 * 数据权限资源
 *
 * @author wayne
 * @date 2021/09/06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "expireDate",callSuper = false)
public class Permission extends BasePo {

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

    /**
     * 过期日期
     */
    private Date expireDate;

    @TableLogic(value = "N",delval = "Y")
    private String deleted = "N";
}

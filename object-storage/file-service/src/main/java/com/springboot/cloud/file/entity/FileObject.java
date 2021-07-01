package com.springboot.cloud.file.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author fengdan
 * @date 2021年07月01日 15:04
 */
@ApiModel
@Data
public class FileObject {
    @NotBlank(message = "不能为空")
    @ApiModelProperty("文件名")
    private String fileName;
    @NotBlank(message = "不能为空")
    @ApiModelProperty("存储空间(桶)")
    private String bucketName;
}

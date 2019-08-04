package com.springboot.cloud.demos.producer.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseQueryForm;
import com.springboot.cloud.demos.producer.entity.param.ProductQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
@Data
public class ProductQueryForm extends BaseQueryForm<ProductQueryParam> {
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "产品名称", required = true)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}

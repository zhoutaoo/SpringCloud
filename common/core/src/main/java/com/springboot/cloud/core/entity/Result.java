package com.springboot.cloud.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Data
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";
    public static final String ERROR_CODE = "-1";
    public static final String ERROR_MESG = "系统异常";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String mesg;
    @ApiModelProperty(value = "处理结果数据信息")
    private T data;

    public Result() {
    }

    public Result(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    public static Result success() {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG);
    }

    public static Result fail() {
        return new Result(ERROR_CODE, ERROR_MESG);
    }

    public static Result fail(Object data) {
        return new Result(ERROR_CODE, ERROR_MESG, data);
    }
}

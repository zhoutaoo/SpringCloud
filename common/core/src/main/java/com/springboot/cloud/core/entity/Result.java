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

    /**
     * @param code
     * @param mesg
     */
    public Result(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    /**
     * @param code
     * @param mesg
     * @param data
     */
    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return
     */
    public static Result success() {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG);
    }

    /**
     * 快速创建失败结果没有返回数据
     *
     * @return
     */
    public static Result fail() {
        return new Result(ERROR_CODE, ERROR_MESG);
    }

    /**
     * 快速创建失败结果并返回结果数据
     *
     * @param data
     * @return
     */
    public static Result fail(Object data) {
        return new Result(ERROR_CODE, ERROR_MESG, data);
    }

    /**
     * 成功code=000000
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return
     */
    public boolean isFail() {
        return !isSuccess();
    }
}

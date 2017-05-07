package com.config;

/**
 * Created by zeject on 2017/5/7.
 */
public enum CustomResponse {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功");

    private Integer code;

    private String msg;

    CustomResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

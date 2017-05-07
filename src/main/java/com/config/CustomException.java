package com.config;

/**
 * Created by zeject on 2017/5/7.
 */
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(CustomResponse customResponse) {
        super(customResponse.getMsg());
        this.code = customResponse.getCode();
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

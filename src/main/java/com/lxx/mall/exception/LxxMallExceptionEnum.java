package com.lxx.mall.exception;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description
 */
public enum LxxMallExceptionEnum {
    NEED_USER_NAME(10001, "用户名不能为空");
    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    LxxMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

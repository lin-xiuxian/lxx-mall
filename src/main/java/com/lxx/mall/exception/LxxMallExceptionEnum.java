package com.lxx.mall.exception;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description
 */
public enum LxxMallExceptionEnum {
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(1003, "密码长度不能少于8位"),
    NAME_EXISTS(1004, "已存在用户，注册失败"),
    INSERT_FAILED(1005, "数据插入失败，请重试"),
    WRONG_PASSWORD(1006, "密码错误"),
    NEED_LOGIN(1007, "用户未登录"),
    UPDATE_FAILED(1008, "更新失败"),
    NEED_ADMIN(1009, "无管理员权限"),
    SYSTEM_ERROR(20000, "系统异常");
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

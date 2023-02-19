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
    NAME_EXISTS(1004, "不允许重名"),
    INSERT_FAILED(1005, "数据插入失败，请重试"),
    WRONG_PASSWORD(1006, "密码错误"),
    NEED_LOGIN(1007, "用户未登录"),
    UPDATE_FAILED(1008, "更新失败"),
    NEED_ADMIN(1009, "无管理员权限"),
    PARA_NOT_NULL(10010, "参数不能为空"),
    REQUEST_PARAM_ERROR(10011, "参数错误"),
    DELETE_FAILED(10012, "删除失败"),
    MKDIR_FAILED(10013, "文件夹创建失败"),
    UPLOAD_FAILED(10014, "文件上传失败"),
    NOT_SALE(10015, "商品状态不可售"),
    NOT_ENOUGH(10016, "商品库存不足"),
    CART_EMPTY(10017, "购物车为空"),
    NO_ENUM(10018, "未找到对应的枚举类"),
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

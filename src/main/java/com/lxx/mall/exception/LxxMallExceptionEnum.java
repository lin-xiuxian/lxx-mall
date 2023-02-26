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
    NO_ORDER(10019, "订单不存在"),
    NOT_YOUR_ORDER(1020, "订单不属于你"),
    WRONG_ORDER_STATUS(1021, "订单状态不符"),
    WRONG_EMAIL(1022, "非法邮件地址"),
    EMAIL_ALREADY_BEEN_REGISTERED(1023, "邮箱已注册"),
    EMAIL_ALREADY_BEEN_SEND(1024, "邮件已发送，若无法收到，请稍后重试"),
    NEED_EMAIL_ADDRESS(1025, "邮箱地址不能为空"),
    NEED_VERIFICATION_CODE(1026, "验证码不能为空"),
    WRONG_VERIFICATION_CODE(1027, "验证码错误"),
    TOKEN_EXPIRED(1028, "token过期"),
    TOKEN_WRONG(1029, "token解析失败"),
    PRICE_TOO_LOW(1030, "价格太低"),
    STOCK_TOO_MANY(1031, "库存不能大于10000"),
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

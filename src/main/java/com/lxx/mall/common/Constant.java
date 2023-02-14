package com.lxx.mall.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description 常量值
 */
public class Constant {

    public static final String LXX_MALL_USER = "lxx_mall_user";
    public static final String SALT = "ig(s*'/2few";

    @Value("${file.upload.dir")
    public static String FILE_UPLOAD_DIR;
}

package com.lxx.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description 常量值
 */
@Component
public class Constant {

    public static final String LXX_MALL_USER = "lxx_mall_user";
    public static final String SALT = "ig(s*'/2few";


    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}

package com.lxx.mall.util;

import com.lxx.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description MD5工具
 */
public class MD5Utils {
    public static String getMD5String(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

    //测试md5加密
    public static void main(String[] args) {
        String md5 = null;
        try {
            md5 = getMD5String("1234");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5);
    }
}

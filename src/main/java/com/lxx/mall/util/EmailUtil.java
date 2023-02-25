package com.lxx.mall.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author 林修贤
 * @date 2023/2/24
 * @description 邮箱校验工具
 */
public class EmailUtil {

    public static boolean isValidEmailAddress(String email){
        boolean result = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}

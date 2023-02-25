package com.lxx.mall.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static String genVerificationCode(){
        List<String> verificationChars = Arrays.asList(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "q", "w",
                "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z",
                "x", "c", "v", "b", "n", "m", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S",
                "D", "F", "G", "H", "J", "K", "L",
                "Z", "X", "C", "V", "B", "N", "M"});
        Collections.shuffle(verificationChars);
        String result = "";
        for(int i = 0; i < 6; i++){
            result += verificationChars.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(EmailUtil.genVerificationCode());
    }
}

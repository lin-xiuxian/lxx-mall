package com.lxx.mall.service;

/**
 * @author 林修贤
 * @date 2023/2/24
 * @description 邮箱接口
 */
public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    boolean savaEmailToRedis(String emailAddress, String verificationCode);
}

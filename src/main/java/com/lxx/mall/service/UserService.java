package com.lxx.mall.service;

import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.model.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description UserService
 */
public interface UserService {
    User getUser();

    void register(String userName, String password) throws LxxMallException;
}

package com.lxx.mall.service.impl;

import com.lxx.mall.model.dao.UserMapper;
import com.lxx.mall.model.pojo.User;
import com.lxx.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description UserService 实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(9);
    }
}

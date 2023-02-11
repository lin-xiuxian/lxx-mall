package com.lxx.mall.service.impl;

import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.dao.UserMapper;
import com.lxx.mall.model.pojo.User;
import com.lxx.mall.service.UserService;
import com.lxx.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

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

    @Override
    public void register(String userName, String password) throws LxxMallException {
        //查询是否重名
        User result = userMapper.selectByName(userName);
        if (result != null){
            throw new LxxMallException(LxxMallExceptionEnum.NAME_EXISTS);
        }

        //写到数据库
        User user = new User();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5String(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        user.setPassword(password);

        int count = userMapper.insertSelective(user);
        if (count == 0){
            throw new LxxMallException(LxxMallExceptionEnum.INSERT_FAILED);
        }

    }
}

package com.lxx.mall.model.dao;

import com.lxx.mall.model.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String userName);

    User selectLogin(@Param("userName")String userName, @Param("password")String password);

    User selectOneByEmailAddress(@Param("emailAddress")String emailAddress);


}
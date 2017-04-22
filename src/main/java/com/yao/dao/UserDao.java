package com.yao.dao;

import com.yao.entity.User;
import com.yao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by yaoqing on 17/4/22.
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}

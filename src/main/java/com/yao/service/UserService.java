package com.yao.service;

import com.yao.entity.User;
import com.yao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yaoqing on 17/4/22.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}

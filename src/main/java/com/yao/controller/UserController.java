package com.yao.controller;

import com.yao.common.Result;
import com.yao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoqing on 17/4/20.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public Result<String> hello(@PathVariable Long id) {
        logger.info("用户id:{}", id);
        String result = "hello, " + userService.findUserById(id).getName();
        return Result.success(result);
    }
}

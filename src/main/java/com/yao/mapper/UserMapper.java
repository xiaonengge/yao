package com.yao.mapper;

import com.yao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * Created by yaoqing on 17/4/22.
 */
public interface UserMapper {

    User findUserById(@Param("id") Long userId);
}

package com.yoyo.mapper;

import com.yoyo.pojo.User;

import java.util.Map;

public interface UserMapper {
    /**
     * 根据条件查询用户
     * @param map
     * @return
     */
    User findUserByCondition(Map<String,Object> map);

}

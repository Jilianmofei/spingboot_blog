package com.yoyo.service;

import com.yoyo.pojo.User;

import java.util.Map;

/**
 * @author Chester
 */
public interface UserService {
    /**
     * 根据条件查询管理员账户
     * @param map
     * @return
     */
    User findUserByCondition(Map<String,Object> map);
}

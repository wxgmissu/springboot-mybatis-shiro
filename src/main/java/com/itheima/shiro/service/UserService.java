package com.itheima.shiro.service;

import com.itheima.shiro.domain.User;

/**
 * @author wxg
 * @creat 2019-07-19-9:40
 */
public interface UserService {
    User findByName(String name);
    User findById(Integer id);

}

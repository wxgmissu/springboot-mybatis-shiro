package com.itheima.shiro.service;

import com.itheima.shiro.domain.User;
import com.itheima.shiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxg
 * @creat 2019-07-19-9:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
   private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}

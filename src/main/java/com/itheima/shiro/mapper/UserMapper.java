package com.itheima.shiro.mapper;

import com.itheima.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wxg
 * @creat 2019-07-19-9:20
 */
public interface UserMapper {

     User findByName(String name);
     User findById(Integer id);
}

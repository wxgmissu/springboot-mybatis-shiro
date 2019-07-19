package com.itheima.shiro.config;

import com.itheima.shiro.domain.User;
import com.itheima.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 * @author wxg
 * @creat 2019-07-18-10:40
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //到数据库找到当前用户
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User user1 = (User) subject.getPrincipal();

        User user = userService.findById(user1.getId());
        info.addStringPermission(user.getPerms());

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken argo) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断账号是否存在
        UsernamePasswordToken token = (UsernamePasswordToken)argo;
        User user = userService.findByName(token.getUsername());
        if (user == null) {
            //用户名不存在
            // return  null时，shiro底层会抛出UnknownAccountException
            return null;
        }
        //2.判断密码是否正确
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "");
        return authenticationInfo;
    }
}

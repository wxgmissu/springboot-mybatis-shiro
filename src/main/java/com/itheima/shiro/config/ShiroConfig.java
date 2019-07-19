package com.itheima.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author wxg
 * @creat 2019-07-18-10:27
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限(url)相关的拦截器
         * 常用的过滤器：
         * anon:无需认证（登陆）即可访问
         * authc:认证后访问
         * user:针对remember me功能
         * perms:获得资源权限才可访问
         * role:获得角色权限才可访问
         *
         */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");
        //资源授权，授权拦截后，shior将直接跳转到未授权页面
        filterMap.put("/add","perms[add]");
        filterMap.put("/update","perms[update]");
        filterMap.put("/*","authc");
        //调整登陆页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权显示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    /*@Qualifier指定UserRealm来源*/
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /*关联realm*/
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name="userRealm")/*方法返回的对象放到容器中*/
    public UserRealm getRealm(){
        return new UserRealm();
    }
    /**
     * 配置shiroDialect用与thymeleaf和shiro标签配合使用
     */


    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}

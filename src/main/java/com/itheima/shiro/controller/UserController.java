package com.itheima.shiro.controller;

import com.itheima.shiro.domain.User;
import com.itheima.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author wxg
 * @creat 2019-07-18-9:37
 */
@Controller
public class UserController {



    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("UserController Hello");
        return "ok";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","SGM:上汽通用");
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }
    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public  String toLogin(){
        return "/login";
    }

    @RequestMapping("/noAuth")
    public  String noAuth(){
        return "/noAuth";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        System.out.println("执行认证controller");
        /**
         * shiro编写登陆认证操作
         */
        //1. 获取subject
        Subject subject = SecurityUtils.getSubject();

        //2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3. 执行登陆方法
        try {
            subject.login(token);
            //登陆成功
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            //登陆失败：用户名不存在
            model.addAttribute("msg","账号不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登陆失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
package com.jx.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @RequestMapping("/user/queryUser")
    public String queryUser(){

        return "查询用户成功！！！";
    }

    @RequestMapping("/sys/log")
    public String sayHello(){

        return "查询日志成功！！！";
    }

    @RequestMapping("/business")
    public String addUser(){

        return "查询业务成功！！！";
    }
}

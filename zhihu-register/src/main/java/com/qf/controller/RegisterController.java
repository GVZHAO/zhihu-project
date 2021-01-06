package com.qf.controller;

import com.qf.pojo.Register;
import com.qf.pojo.UserInfo;
import com.qf.pojo.req.RegisterReq;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    //登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody Register register){
        System.out.println("register = " + register);
        BaseResp baseResp = registerService.login(register);
        return baseResp;
    }

    //发送邮件
    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    public BaseResp sendEmail(@RequestParam("email") String email){
//        System.out.println("这是邮箱地址" + email);
       BaseResp baseResp = registerService.sendEmail(email);
       return baseResp;
    }

    //注册
    @RequestMapping(value = "/regist",method =RequestMethod.POST)
    public BaseResp regist(@RequestBody RegisterReq registerReq){
        BaseResp baseResp = registerService.regist(registerReq);
        return baseResp;
    }

}

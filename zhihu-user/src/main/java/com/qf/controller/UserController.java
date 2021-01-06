package com.qf.controller;

import com.qf.pojo.UserInfo;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/insertUserInfo",method = RequestMethod.POST)
    public BaseResp insertUserInfo(@RequestBody UserInfo userInfo,HttpServletRequest request){
        BaseResp baseResp = userInfoService.insertUserInfo(userInfo,request);
        return baseResp;
    }

    //查询个人信息
    @RequestMapping(value = "/findUserInfo",method = RequestMethod.POST)
    public BaseResp findUserInfo(@RequestParam String username){
        BaseResp baseResp = userInfoService.findUserInfo(username);
        return baseResp;
    }

}

package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.UserInfo;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserInfoService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public BaseResp insertUserInfo(UserInfo userInfo, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        BaseResp baseResp = new BaseResp();
        if (token != null) {
            Map map = jwtUtils.Verify(token);
            Integer registerId = (Integer) map.get("id");//以登录用户的ID
//            System.out.println("registerId = " + registerId);
            String name = userInfo.getName();

            Integer integer = userMapper.insertUserInfo(userInfo);
            Integer userInfoId = userMapper.getUserInfoIdByName(name);
            System.out.println("userInfoId = " + userInfoId);
            if (integer != null) {
                Integer integer1 = userMapper.insertUserInfoAndRegister(registerId, userInfoId);
                //需要把用户的id和register的id存入中间表里联合起来
                baseResp.setMsg("完善信息完成");
                baseResp.setCode(200);
                return baseResp;
            }
            baseResp.setCode(201);
            baseResp.setMsg("完善信息失败");
            return baseResp;
        }
        baseResp.setMsg("token为空");
        baseResp.setCode(201);
        return baseResp;
    }

    @Override
    public BaseResp findUserInfo(String username) {
        UserInfo userInfo = userMapper.findUserInfo(username);
        BaseResp baseResp = new BaseResp();
        baseResp.setMsg("个人详情");
        baseResp.setCode(200);
        baseResp.setData(userInfo);
        return baseResp;
    }
}

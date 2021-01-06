package com.qf.service.impl;

import com.qf.clients.UserClients;
import com.qf.dao.RegisterMapper;
import com.qf.pojo.Register;
import com.qf.pojo.UserInfo;
import com.qf.pojo.req.RegisterReq;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.RegisterService;
import com.qf.utils.CookieUtils;
import com.qf.utils.EmailUtils;
import com.qf.utils.JWTUtils;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserClients userClients;

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public BaseResp login(Register register) {

        String username = register.getUsername();
        Register registered = registerMapper.findRegisterByUsername(username);
        BaseResp baseResp = new BaseResp();
        if (registered == null) {
            baseResp.setCode(201);
            baseResp.setMsg("没有此用户");
            return baseResp;
        }
        //验证密码
        if (!registered.getPassword().equals(register.getPassword())) {
            baseResp.setMsg("密码错误");
            baseResp.setCode(201);
            return baseResp;
        }
//全部正确,可以登陆
        JWTUtils jwtUtils = new JWTUtils();
        //放置在 jwt中的负载部分
        Map map = new HashMap<>();
        map.put("register", registered.toString());
        map.put("id", registered.getId());
        map.put("username",registered.getUsername());
        String token = jwtUtils.token(map);
        baseResp.setCode(200);
        baseResp.setData(token);
        baseResp.setMsg("登陆成功");
        return baseResp;
    }

    //发送邮件
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public BaseResp sendEmail(String email) {
        BaseResp baseResp = emailUtils.sendEmail(email);
        return baseResp;
    }

    //注册

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public BaseResp regist(RegisterReq registerReq) {
        BaseResp baseResp = new BaseResp();
        String code = registerReq.getCode();
        String email = registerReq.getEmail();
        String codeRedis = (String)redisUtils.get(email);

        Register register = registerMapper.findRegisterByEmail(email);

        if (code != null && code.equals(codeRedis)){

            if (register == null){
                Integer integer = registerMapper.regist(registerReq);
                baseResp.setCode(200);
                baseResp.setMsg("注册成功");
                return baseResp;
            }else{
                baseResp.setMsg("用户名已被占用");
                baseResp.setCode(201);
                return baseResp;
            }
        }else{
            baseResp.setCode(201);
            baseResp.setMsg("验证为空");
            return baseResp;
        }
    }

}

package com.qf.utils;

import com.qf.pojo.resp.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailUtils {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public BaseResp sendEmail(String email) {
//        System.out.println("email = " + email);
        BaseResp baseResp = new BaseResp();

        if (email != null) {

            Random random = new Random();
            StringBuffer code = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                int i1 = random.nextInt(10);
                code.append(i1);
            }
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("注册验证码");
            simpleMailMessage.setText("您的验证码为:"+code.toString()+",验证码有效期为5分钟");
            javaMailSender.send(simpleMailMessage);
            redisUtils.set(email, code.toString());
            redisUtils.expire(email, 300*60*60);
            baseResp.setMsg("验证码发送成功!");
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setMsg("邮箱不能为空!");
        baseResp.setCode(201);
        return baseResp;
    }
}

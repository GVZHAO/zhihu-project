package com.qf.service;

import com.qf.pojo.Register;
import com.qf.pojo.UserInfo;
import com.qf.pojo.req.RegisterReq;
import com.qf.pojo.resp.BaseResp;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {
    public BaseResp login(Register register);

    BaseResp sendEmail(String email);

    BaseResp regist(RegisterReq registerReq);

}

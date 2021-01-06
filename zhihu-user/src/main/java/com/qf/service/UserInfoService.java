package com.qf.service;

import com.qf.pojo.UserInfo;
import com.qf.pojo.resp.BaseResp;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoService {
    public BaseResp insertUserInfo(UserInfo userInfo, HttpServletRequest request);

    BaseResp findUserInfo(String username);
}

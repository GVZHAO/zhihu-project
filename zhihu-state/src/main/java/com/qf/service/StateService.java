package com.qf.service;

import com.qf.pojo.resp.BaseResp;

public interface StateService {
    BaseResp findState(String stateName);

    BaseResp createStaticThymeleaf(Integer id);
}

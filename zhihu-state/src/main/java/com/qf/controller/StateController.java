package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    //查询此用户发布的文章
    @RequestMapping(value = "/findState",method = RequestMethod.POST)
    public BaseResp findState(@RequestParam String stateName){
        BaseResp baseResp = stateService.findState(stateName);
        return baseResp;
    }

    //创建静态页面
    @RequestMapping(value = "/createStaticThymeleaf",method = RequestMethod.POST)
    public BaseResp createStaticThymeleaf(@RequestBody Map map){
        BaseResp baseResp = stateService.createStaticThymeleaf((Integer)map.get("id"));
        return baseResp;
    }
}

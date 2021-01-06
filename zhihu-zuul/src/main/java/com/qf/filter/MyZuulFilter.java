package com.qf.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qf.pojo.resp.BaseResp;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
public class MyZuulFilter extends ZuulFilter {

    private static final List URL_LIST= new ArrayList<>();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        if (URL_LIST.contains(requestURI)){
            //放行login请求
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //进行用户的验证 是否登录
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        //声明返回值
        BaseResp baseResp = new BaseResp();
        //从request中获取token
        Cookie[] cookies = request.getCookies();
        if (cookies==null||cookies.length==0){
            response.setContentType("application/json;charset=utf-8");
            baseResp.setMsg("未登录");
            baseResp.setCode(201);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.print(JSONObject.toJSON(baseResp));
            currentContext.setSendZuulResponse(false);
        }
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        //使用jwt进行解密
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);

        if (verify==null||verify.get("username")==null){
            response.setContentType("application/json;charset=utf-8");
            baseResp.setMsg("登录失效，重新登录");
            baseResp.setCode(201);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.print(JSONObject.toJSON(baseResp));
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }

    //无参构造
    public MyZuulFilter(){
        URL_LIST.add("/qfshop-user/login");
//        URL_LIST.add("/qfshop-goods/findAll");
    }
}

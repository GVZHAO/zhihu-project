package com.qf.service.impl;

import com.qf.dao.StateMapper;
import com.qf.pojo.State;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.StateService;
import com.qf.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateMapper stateMapper;

    @Override
    public BaseResp findState(String stateName) {
        State state = stateMapper.findState(stateName);
        BaseResp baseResp = new BaseResp();
        baseResp.setData(state);
        baseResp.setMsg("用户所发表的文章");
        baseResp.setCode(200);
        return baseResp;
    }


    //1.我们要读取已经写好的静态化的模板内容
    //2.通过传入的id拿到该商品的具体 信息。
    //3.将静态化模板与信息相结合，获取到该商品的静态化页面
    //4.放置再云上，或者自己搭建的静态化服务器中

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    UploadUtils uploadUtils;


    @Override
    public BaseResp createStaticThymeleaf(Integer id) {
        //声明获取静态化的页面的对象
        Context context = new Context();
        //查询数据
//        TGoods byId = goodsMapper.findById(id);
        //将页面的内容与数据相结合
//        context.setVariable("goods", byId);
        String goodsTemplate = templateEngine.process("GoodsTemplate", context);
        //System.out.println(goodsTemplate);
        //声明一个uuid
        UUID uuid = UUID.randomUUID();
        /*BaseResp baseResp = uploadUtils.uploadString(goodsTemplate);
        //获取到上传后的url
        Object data = baseResp.getData();

        //将静态化页面的地址放置再数据中
        byId.setStaticUrl(data.toString());*/
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(new File("E:/source/file/" + uuid.toString() + ".html"));
            file.write(goodsTemplate.getBytes("utf-8"));
            file.close();
//            byId.setStaticUrl(uuid.toString() + ".html");
//            goodsMapper.updateById(byId);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

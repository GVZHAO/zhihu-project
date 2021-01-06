package com.qf.pojo;

import lombok.Data;

//完善个人信息类
@Data
public class UserInfo {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String addr;
    private String info;
    private String img;

}

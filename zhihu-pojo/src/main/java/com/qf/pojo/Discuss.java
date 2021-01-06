package com.qf.pojo;

import lombok.Data;

//评论表的实体类
@Data
public class Discuss {
    private Integer id;
    private Integer userId;
    private Integer stateId;
    private String stateDiscuss;

}

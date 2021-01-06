package com.qf.pojo;

import lombok.Data;

//点赞表
@Data
public class PointPraise {
    private Integer id;
    private Integer userId;
    private Integer stateId;
    private String praise;

}

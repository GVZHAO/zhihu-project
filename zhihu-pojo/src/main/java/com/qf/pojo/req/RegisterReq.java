package com.qf.pojo.req;

import lombok.Data;

@Data
public class RegisterReq {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String code;
}

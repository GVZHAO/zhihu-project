package com.qf.dao;

import com.qf.pojo.Register;
import com.qf.pojo.req.RegisterReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterMapper {
    Register findRegisterByUsername(@Param("username") String username);

    Register findRegisterByEmail(@Param("email") String email);

    Integer regist(RegisterReq registerReq);
}

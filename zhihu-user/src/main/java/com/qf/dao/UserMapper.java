package com.qf.dao;

import com.qf.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Integer insertUserInfo(UserInfo userInfo);

    Integer insertUserInfoAndRegister(@Param("registerId") Integer registerId,@Param("userInfoId") Integer userInfoId);

    Integer getUserInfoIdByName(@Param("name") String name);

    UserInfo findUserInfo(String username);
}

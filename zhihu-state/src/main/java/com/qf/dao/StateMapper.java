package com.qf.dao;

import com.qf.pojo.State;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StateMapper {
    State findState(@Param("stateName") String stateName);
}

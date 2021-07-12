package com.atguigu.cloud.Mapper;

import com.atguigu.cloud.Pojo.LoginUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface MyLoginDao {


    @Select("select * from user where userName=#{login.userName} and passWord=#{login.passWord}")
    public LoginUser getLogin(@Param("login") LoginUser loginUser);

}

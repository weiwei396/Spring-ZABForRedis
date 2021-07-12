package com.atguigu.cloud.Service;

import com.atguigu.cloud.Pojo.LoginUser;
import org.springframework.stereotype.Service;


public interface UserService {

    public LoginUser getLogin(LoginUser loginUser);
}

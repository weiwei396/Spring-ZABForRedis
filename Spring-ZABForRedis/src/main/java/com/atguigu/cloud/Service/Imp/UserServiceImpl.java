package com.atguigu.cloud.Service.Imp;

import com.atguigu.cloud.Mapper.MyLoginDao;
import com.atguigu.cloud.Pojo.LoginUser;
import com.atguigu.cloud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MyLoginDao myLoginDao;


    @Override
    public LoginUser getLogin(LoginUser loginUser) {
        return myLoginDao.getLogin(loginUser);
    }
}

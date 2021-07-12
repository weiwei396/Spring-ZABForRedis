package com.atguigu.cloud.Config;

import com.atguigu.cloud.Pojo.LoginUser;

/**
 * ThreadLocal存储用户信息
 */
public class ThreadLocalUtils {


    public static ThreadLocal<LoginUser> LoginThreadLocal=new ThreadLocal<>();


    public static void set(LoginUser loginUser){
        LoginThreadLocal.set(loginUser);
    }

    public static LoginUser get(){
        return LoginThreadLocal.get();
    }

    public static void  remove(){
        LoginThreadLocal.remove();
    }

}

package com.atguigu.cloud.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.cloud.Config.JWTUtils;
import com.atguigu.cloud.Config.RedisUtils;
import com.atguigu.cloud.Config.ThreadLocalUtils;
import com.atguigu.cloud.Pojo.LoginUser;
import com.atguigu.cloud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    @PostMapping("/login")
    public JSONObject Login(@RequestBody LoginUser loginUser){
        LoginUser login = userService.getLogin(loginUser);
        JSONObject jsonObject=new JSONObject();
        if(login==null){
            jsonObject.put("501","用户不存在");
        }else{
            String token= JWTUtils.CreateToke(login);
            redisTemplate.opsForValue().set(token,JSON.toJSONString(login),30, TimeUnit.MINUTES);
            jsonObject.put("token",token);
            jsonObject.put("message","登录成功");
            jsonObject.put("code","1");
        }
        return jsonObject;
    }

    @PostMapping("getUser")
    public LoginUser get(){
        LoginUser loginUser=ThreadLocalUtils.get();
        return loginUser;
    }
}

package com.atguigu.cloud.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtils {


    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;


    //设置相应的值
    public static void set(String key,String values,Long time){
        redisTemplate.opsForValue().set(key,values,time, TimeUnit.SECONDS);
    }

    //获取相应的过期时间
    public static Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }


    public static boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}

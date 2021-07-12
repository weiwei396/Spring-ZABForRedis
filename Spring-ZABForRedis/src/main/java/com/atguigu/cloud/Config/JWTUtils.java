package com.atguigu.cloud.Config;


import com.alibaba.fastjson.JSON;
import com.atguigu.cloud.Pojo.LoginUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {


    private static final String SECRET="jwt_secret";

    public static String CreateToke(LoginUser loginUser){
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        String Login= JSON.toJSONString(loginUser);
        return JWT.create()
                .withSubject(Login)
                .sign(algorithm);
    }


    public static boolean checkSign(String Token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(Token);
            return true;
        }catch (JWTVerificationException e){
            throw new RuntimeException("token 无效，请重新获取");
        }
    }


    public static LoginUser getLoginUser(String Token){
        try {
           return JSON.parseObject(JWT.decode(Token).getSubject(),LoginUser.class);
        }catch (JWTDecodeException e){
            e.printStackTrace();
        }
        return null;
    }





}

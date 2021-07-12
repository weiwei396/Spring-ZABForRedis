package com.atguigu.cloud.Interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.cloud.Config.JWTUtils;
import com.atguigu.cloud.Config.RedisUtils;
import com.atguigu.cloud.Config.ThreadLocalUtils;
import com.atguigu.cloud.Pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 配置相应的拦截，对于分布式项目可以在网关中进行拦截
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("#{'${exclude.url}'.split(',')}")
    private String[] excludeUrl;


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI=request.getRequestURI();
        //排除不需要验证的URL地址
        URI=URI.substring(URI.lastIndexOf("/"),URI.length());
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.values());
        List<String> myContains = Arrays.asList(excludeUrl);
        if(myContains.contains(URI)){
            return true;
        }
        String Token=request.getHeader("token");
        LoginUser loginUser=JWTUtils.getLoginUser(Token);
        if(!redisTemplate.hasKey(Token)){
            throw new RuntimeException("token已经过期");
        }
        if(loginUser==null){
            throw new RuntimeException("用户不存在，请重新登录");
        }
        ThreadLocalUtils.set(loginUser);
        //30min过期，当前用户再次请求时刷新请求时间，重为30min
        redisTemplate.opsForValue().set(Token,JSON.toJSONString(loginUser),30, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
//避免内存泄漏，依次请求完成应该对ThreadLocal进行清除
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtils.remove();
    }
}

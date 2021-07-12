# Spring-ZABForRedis
项目主要使用了技术栈：
  （1）SpringBoot：以SpringBoot作为项目的web架构
   (2)Redis：缓存对于的一些Token，当用户登录之后就会生成一个Token，每次请求都要携带这个Token值，如果用户再次请求会重置token过期时间
   (3)ThreadLocal:主要使用ThreadLocal线程私有这个特性来存储用户的ID，方便后续请求直接调用
  
该项目为单体式的架构，可以在内部直接用拦截器进行拦截，如何进行校验，对于分布式项目的话，可以直接在网关处进行拦截。

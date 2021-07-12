package com.atguigu.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author weiwei
 */
@SpringBootApplication
@MapperScan("com.atguigu.cloud.Mapper")
public class RedissionMain {
    public static void main(String[] args) {
        SpringApplication.run(RedissionMain.class, args);
    }
}

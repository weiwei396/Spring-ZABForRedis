package com.atguigu.cloud.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {


    public String id;

    public String userName;

    public String passWord;

    public LoginUser(String userName,String passWord){
        this.userName=userName;
        this.passWord=passWord;
    }

}

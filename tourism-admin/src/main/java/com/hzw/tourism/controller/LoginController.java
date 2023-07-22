package com.hzw.tourism.controller;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController{

    @Autowired
    private LoginService loginSercice;
    @PostMapping("/admin/login")
    public ResponseResult login(@RequestBody Admin admin){
       return loginSercice.login(admin);
    }

    @GetMapping("/loginOut")
    public ResponseResult logout(){
        return loginSercice.logout();
    }

    

}

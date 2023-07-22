package com.hzw.tourism.service;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Admin;

public interface LoginService {

    ResponseResult login(Admin admin);

    ResponseResult logout();

}

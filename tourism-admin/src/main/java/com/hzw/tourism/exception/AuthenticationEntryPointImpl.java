package com.hzw.tourism.exception;

import com.alibaba.fastjson.JSON;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        ResponseResult result=new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登录");
        String josn = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,josn);

    }
}

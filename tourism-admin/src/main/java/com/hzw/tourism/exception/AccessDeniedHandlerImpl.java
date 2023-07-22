package com.hzw.tourism.exception;

import com.alibaba.fastjson.JSON;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
            ResponseResult result=new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"你的权限不足");
            String josn = JSON.toJSONString(result);
            //处理异常
            WebUtils.renderString(response,josn);
    }
}


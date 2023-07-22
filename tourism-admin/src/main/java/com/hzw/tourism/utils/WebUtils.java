package com.hzw.tourism.utils;

import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     * @param response 渲染对象
     * @param str 渲染字符串
     * @return
     */
    public static String renderString(HttpServletResponse response,String str){

        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     *获取当前登录人id
     * @return
     */
    public static Long getUserId(){
        //获取SecorityContextHolder中的id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getAdmin().getId();
        return userid;
    }

    /**
     * 生成随机数
     * @return
     */
    public static String getCode(){
        int random = (int) (Math.random() *  10000);
        String code= String.format("%10d", random+System.currentTimeMillis());
        return code;
    }

    /**
     * 获取当前登录人信息
     * @return
     */
    public static Admin getUser(){
        //获取SecorityContextHolder中的id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getAdmin();
    }

}

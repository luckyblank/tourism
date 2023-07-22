package com.hzw.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.LoginUser;
import com.hzw.tourism.mapper.MenuMapper;
import com.hzw.tourism.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceIml implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;
    
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //查询用户信息
        LambdaQueryWrapper<Admin> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (Objects.isNull(admin)){
            throw new RuntimeException("用户名或密码错误");
        }
      //查询对应的权限信息
    //    ArrayList<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuMapper.selectPermsByUserId(admin.getId());
        //把数据封装乘UserDetails 返回
        return new LoginUser(admin,list);
    }
}

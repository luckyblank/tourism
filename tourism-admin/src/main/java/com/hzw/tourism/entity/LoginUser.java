package com.hzw.tourism.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
  private Admin admin;
  private List<String> permissions;
  //不存入redis 因为redis不序化
    @JSONField(serialize = false)
  private List<SimpleGrantedAuthority> authorities;

    public LoginUser(Admin admin, List<String> permissions) {
        this.admin = admin;
        this.permissions = permissions;
    }

    //获取权限信息  权限集合转换
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
  if(authorities!=null){
      return authorities;
  }
        //把permissions中的String类型的权限信息封装乘SimpleGrantedAuthorities对象
//       newList=new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//        }
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }
//获取密码
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

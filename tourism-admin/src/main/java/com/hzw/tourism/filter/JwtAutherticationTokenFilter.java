package com.hzw.tourism.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hzw.tourism.comon.RedisCache;
import com.hzw.tourism.entity.LoginUser;
import com.hzw.tourism.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
@Component
public class JwtAutherticationTokenFilter extends OncePerRequestFilter {
   @Autowired
   private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token"); // “null”
        System.err.println(token);
        if (StringUtils.isBlank(token)){
            // 复现一下这个错误
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userid;

        try {
            Claims claims = JwtUtil.parseJWT(token);
             userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("token非法");
        }
        //从redis中获取用户信息
         String redisKey="login:"+userid;
        /*
        可能是序列器存在问题
        @JSONField(serialize = false)
  private List<SimpleGrantedAuthority> authorities;

  这个字段 不会进行序列化，可能是这里的影响
         */
//         LoginUser loginUser= redisCache.getCacheObject(redisKey);

        // 1、定义一个空的容器
        LoginUser loginUser = new LoginUser();
        // 2、从Redis中获取用户信息缓存
        Object cacheObject = redisCache.getCacheObject(redisKey);
        // 3、 判断是否存在响应的缓存
        if (Objects.isNull(cacheObject)){
            // 4、 缓存不存在,用户不合法
             throw  new RuntimeException("用户未登录");
         }
        /*
        5、由于第三步已经将空的扔出去,所以到这里一定是存在用户信息的
这一步,将Redis中查出来的用户信息,传递给LoginUser的PoJo,
避免了上面JSON注解不序列化造成的无法完全赋值以致于序列化失败
也就是说,上边的注解禁止了authorities这个属性的序列化,那么loginUser就只有两个属性
但是你从Redis缓存中拿到的对象有三个属性(include authorities)所以,两个对象就不同了,不同的对象就会cast失败
         */
        BeanUtils.copyProperties(cacheObject,loginUser);

        //存入SecurityContextHolder
        //获取权限信息 封装到sAuthentication
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       //放行
        filterChain.doFilter(request,response);
    }
}

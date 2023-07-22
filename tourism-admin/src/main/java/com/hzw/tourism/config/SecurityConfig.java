package com.hzw.tourism.config;

import com.hzw.tourism.filter.JwtAutherticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启权限配置
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAutherticationTokenFilter jwtAutherticationTokenFilter;
   @Autowired
   private AuthenticationEntryPoint authenticationEntryPoint;
   @Autowired
   private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //放行


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //关闭csrf
            .csrf().disable()
            //不通过session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            //对于登录接口 允许匿名访问
            .antMatchers(
                    "/admin/login",
                    "/file/upload",
                    "/file/uploadUser",
                    "/admin/saveOrUpdateUser",
                    "/admin/findProvinceAll",
                    "/car/listPage",
                    "/car/findCarById",
                    "/hotel/findHotelById",
                    "/hotel/listPage",
                    "/hotel/listHotelSpot",
                    "/hotel/listSpotHotel",
                    "/insurance/findInsuranceById",
                    "/insurance/listPage",
                    "/notic/getByNoticById",
                    "/notic/getByNoticAll",
                    "/travelRoute/findById",
                    "/travelRoute/listPage"
                ).anonymous()  ///admin/login
                .antMatchers(
                    "/favicon.ico",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/v2/**",
                    "/swagger-ui.html/**",
                    "/doc.html").anonymous()
                .antMatchers(
                        "/druid/**"
                ).anonymous()
                //除上面外的所有请求鉴权认证
                .anyRequest().authenticated();
       //添加过滤器
        http.addFilterBefore(jwtAutherticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
      //添加异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    //添加允许跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

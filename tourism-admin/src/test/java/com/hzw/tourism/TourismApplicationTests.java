package com.hzw.tourism;

import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.LoginUser;
import com.hzw.tourism.mapper.MenuMapper;
import com.hzw.tourism.mapper.AdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@SpringBootTest
class TourismApplicationTests {
@Autowired
private AdminMapper adminMapper;

@Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender mailSender;

    @Test
    void contextLoads() {
        List<Admin> admins = adminMapper.selectList(null);
        LoginUser loginUser = new LoginUser();
        System.out.println(loginUser.getAdmin().getId());
        System.out.println(admins);
    }
    @Test
    public void TestBCrtpPasswordEncoder(){
        //$2a$10$8V6bYqSU8/S5okEKnKh2l.9rTbX7aM5i/s42FYe1KOAKDtbPFhMVa
        //System.out.println(passwordEncoder.matches("1234","$2a$10$8V6bYqSU8/S5okEKnKh2l.9rTbX7aM5i/s42FYe1KOAKDtbPFhMVa"));
        String encode= passwordEncoder.encode("1234");
        String encode1=passwordEncoder.encode("1234");
        boolean encoding = passwordEncoder.upgradeEncoding("$2a$10$8V6bYqSU8/S5okEKnKh2l.9rTbX7aM5i/s42FYe1KOAKDtbPFhMVa");
        System.out.println(encode);
        System.out.println(encode1);

    }
    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void testUserMapper(){
//        List<String> list = menuMapper.selectPermsByUserId(2L);
//        System.out.println(list);
        int flag=1;
        for (int i = 0; i <100 ; i++) {
            System.out.println(flag++);
        }

    }

    @Test
    public void sendSampleMail() {
        // 简单邮件类
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 寄件人，默认是配置的username
        mailMessage.setFrom(mailProperties.getUsername());
        // 收件人，支持多个收件人
        mailMessage.setTo("2071252817@qq.com");
        // 邮件主题
        mailMessage.setSubject("Test simple mail");
        // 邮件的文本信息
        mailMessage.setText("Hello this is test mail from java");
        // 发送邮件
        mailSender.send(mailMessage);
    }



    @Test
    public void test1(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间戳
        //long l = System.currentTimeMillis();
        //如果你数据库存储的时间戳类型为string，就需要将string字符串转为long类型
        String currentTime = "1602384121000";
        long l = Long.parseLong(currentTime);
        String format = sdf.format(l);
        System.out.println("日期格式："+format);
        //输出：日期格式：2020-10-11 10:42:01
    }





}

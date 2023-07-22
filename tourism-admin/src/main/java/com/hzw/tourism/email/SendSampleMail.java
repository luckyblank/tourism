package com.hzw.tourism.email;

import com.hzw.tourism.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendSampleMail {
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender mailSender;

    public  void sendMail(Admin admin){
        // 简单邮件类
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 寄件人，默认是配置的username
        mailMessage.setFrom(mailProperties.getUsername());
        // 收件人，支持多个收件人
        mailMessage.setTo(admin.getEmail());
        // 邮件主题
        mailMessage.setSubject("欢迎注册秃游网，这是您的账号信息");
        // 邮件的文本信息
        mailMessage.setText("账号："+admin.getUsername()+"密码："+admin.getPassword());
        // 发送邮件
        mailSender.send(mailMessage);
    }

}

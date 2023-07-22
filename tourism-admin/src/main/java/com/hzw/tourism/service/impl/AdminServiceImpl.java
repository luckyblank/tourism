package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.DTO.AdminDTO;
import com.hzw.tourism.entity.Province;
import com.hzw.tourism.constant.RedisConstants;
import com.hzw.tourism.vo.ProvinceVo;
import com.hzw.tourism.mapper.AdminMapper;
import com.hzw.tourism.qo.AdminQuery;
import com.hzw.tourism.qo.UserQuery;
import com.hzw.tourism.service.AdminService;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private MailProperties mailProperties;
    @Resource
    private JavaMailSender mailSender;


    /**
     * 用户注册在线人数
     *
     * @return
     */
    @Override
    public Integer count1() {
        String key = RedisConstants.CACHE_ADMIN_COUNT0_KEY;
        if (redisTemplate.hasKey(key)) {
            String count0 = redisTemplate.opsForValue().get(key);
            Integer countUser = (Integer) JSON.parse(count0);
            log.info("走缓存......");
            return countUser;
        } else {
            Integer count = lambdaQuery().eq(Admin::getState, 1).eq(Admin::getUserType, 2).count();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(count), CommonConstants.FOUR, TimeUnit.HOURS);
            log.info("存入缓存......");
            return count;
        }
    }

    /**
     * 查询用户注册各省份的数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findProvinceByid() throws Exception {
        List<ProvinceVo> porvicesList = adminMapper.findProvinceByid();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (ProvinceVo provinceVo : porvicesList) {
            Map<String, Object> map0 = new HashMap<>();
            map0.put("key", provinceVo.getAddr());
            map0.put("value", provinceVo.getCount());
            mapList.add(map0);
        }
        return mapList;
    }

    /**
     * 查询所有省份
     *
     * @return
     */
    @Override
    public List<Province> findfindProvinceAll() {
        List<Province> porvicesList = adminMapper.findfindProvinceAll();
        System.out.println(porvicesList);
        return porvicesList;
    }

    @Override
    public void saveOrUpdateUser(Admin admin) throws Exception  {
        String userKey = RedisConstants.CACHE_ADMIN_KEY+admin.getId();
        Date date = new Date();
        if (admin.getId() == null) {
            admin.setAddTime(date);
            sendEmail(admin);
            //3.获取用户输入密码加密
            String newPassword = passwordEncoder.encode(admin.getPassword());
            admin.setPassword(newPassword);
            int insert = adminMapper.insert(admin);
            if (insert == 0) {
                throw new Exception("新增失败");
            }
        } else {
            admin.setModifyTime(date);
            adminMapper.updateById(admin);
            redisTemplate.delete(userKey);
        }
    }

    public void sendEmail(Admin admin) {
        // 简单邮件类
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 寄件人，默认是配置的username
        mailMessage.setFrom(mailProperties.getUsername());
        // 收件人，支持多个收件人
        mailMessage.setTo(admin.getEmail());
        // 邮件主题
        mailMessage.setSubject("欢迎注册秃游网，这是您的账号信息");
        // 邮件的文本信息
        mailMessage.setText("账号：" + admin.getUsername() + "  " + "密码：" + admin.getPassword());

        // 发送邮件
        mailSender.send(mailMessage);
    }

    @Override
    public void updateUser(Admin admin) {
        String userKey = RedisConstants.CACHE_ADMIN_KEY+admin.getId();
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        admin.setModifyTime(date);
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userId != null, Admin::getId, userId);
        update(admin, queryWrapper);
        adminMapper.updateById(admin);
        redisTemplate.delete(userKey);
    }

    @Override
    public ResponseResult findUsersById() {
        Long userId = WebUtils.getUserId();
        Admin user = getById(userId);
        if (user == null) {
            return ResponseResult.fail();
        }
        return ResponseResult.success(user);
    }

    @Override
    public ResponseResult findAdmin() {
        Long adminId = WebUtils.getUserId();
        Admin admin = getById(adminId);
        if (admin == null || admin.getUserType() == 2) {
            return ResponseResult.fail();
        }
        return ResponseResult.success(admin);

    }

    @Override
    public ResponseResult saveOrUpdateAdmin(Admin admin) {
        String key = RedisConstants.CACHE_ADMIN_KEY + admin.getId();
        Date date = new Date();
        if (admin.getId() == null) {
            //新增管理员
            admin.setUserType(1);
            admin.setAddTime(date);
            sendEmail(admin);
            //邮件发送消息
            //3.获取管理员输入密码加密
            String newPassword = passwordEncoder.encode(admin.getPassword());
            admin.setPassword(newPassword);
            save(admin);
            return new ResponseResult(CommonConstants.SUCCESS, "新增管理员成功！！");
        } else {
            admin.setModifyTime(date);
            updateById(admin);
            redisTemplate.delete(key);
            return new ResponseResult(CommonConstants.SUCCESS, "更新管理员成功！！");
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws Exception {
        updateById(admin);
    }


    @Override
    public ResponseResult findAdminById(Long id) {
        String key = RedisConstants.CACHE_ADMIN_KEY + id;
        String findadmin = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(findadmin)) {
            Admin Admin = JSON.parseObject(findadmin, Admin.class);
            log.info("走缓存....");
            return ResponseResult.success(Admin);
        }
        if ("".equals(findadmin)) {
            return new ResponseResult(400, "该用户不存在！！");
        }

        Admin Admin = adminMapper.selectById(id);
        if (Admin == null) {
            redisTemplate.opsForValue().set(key, "", 2, TimeUnit.MINUTES);
            return new ResponseResult(400, "没有该用户！！");
        }
        redisTemplate.opsForValue().set(key, JSON.toJSONString(Admin), CommonConstants.FOUR, TimeUnit.HOURS);
        log.info("存入缓存....");
        return ResponseResult.success(Admin);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        String adminKey = RedisConstants.CACHE_ADMIN_KEY+id;
        adminMapper.deleteById(id);
        redisTemplate.delete(RedisConstants.CACHE_ADMIN_COUNT0_KEY);
        redisTemplate.delete(RedisConstants.CACHE_ADMIN_COUNT1_KEY);
        redisTemplate.delete(adminKey);
    }

    @Override
    public void updateStates(Admin admin) throws Exception {
        String adminKey = RedisConstants.CACHE_ADMIN_KEY+admin.getId();
        admin.setModifyTime(new Date());
        LambdaUpdateWrapper<Admin> lambdaQueryWrapper1 = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper1.eq(Admin::getId, admin.getId())
                .set(Admin::getState, admin.getState());
       adminMapper.update(null, lambdaQueryWrapper1);
        redisTemplate.delete(adminKey);
    }


    @Override
    public List<Map<String, Object>> stateAll() throws Exception{
        Integer logout = lambdaQuery().eq(Admin::getState, 0).eq(Admin::getDeleteStatus, 0).count();
        Integer publish = lambdaQuery().eq(Admin::getState, 1).eq(Admin::getDeleteStatus, 0).count();
        Integer waitPublish = lambdaQuery().eq(Admin::getState, 2).eq(Admin::getDeleteStatus, 0).count();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map0 = new HashMap<>();
        map0.put("key", "注销");
        map0.put("value", logout);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", "发布");
        map1.put("value", publish);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", "待发布");
        map2.put("value", waitPublish);
        mapList.add(map0);
        mapList.add(map1);
        mapList.add(map2);
        return Optional.ofNullable(mapList).orElse(Collections.emptyList());
    }


    @Override
    public IPage<Admin> listPage(UserQuery query) throws Exception{
        //分页
        Page<Admin> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getUsername()), Admin::getUsername, query.getUsername());
        lambdaQueryWrapper.eq(Admin::getUserType, 2);
        //分页
        IPage<Admin> result = adminMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }

    @Override
    public IPage<Admin> listPageAdmin(AdminQuery query) throws Exception {
        //分页
        int corrent = query.getCurrentPage();
        int pageSize = query.getPageSize();
        Page<Admin> page = new Page<>(corrent, pageSize);
        //条件查询
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getUsername()), Admin::getUsername, query.getUsername());
        lambdaQueryWrapper.ne(Admin::getUserType, 2);
        //分页
        IPage<Admin> result = adminMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }

    @Override
    public ResponseResult updatePassword(AdminDTO adminDTO) {
        Long userId = WebUtils.getUserId();
        Admin user = getById(userId);
        String adminKey = RedisConstants.CACHE_ADMIN_KEY+user.getId();
        LambdaUpdateWrapper<Admin> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        //1.判断是否输入
        if (adminDTO == null) {
            return new ResponseResult(400, "请输人旧密码和新密码");
        }
        //2.判断输入密码是否与数据库匹配
        if (passwordEncoder.matches(adminDTO.getPassword(), user.getPassword())) {
            //3.获取用户输入新密码加密
            String newPassword = passwordEncoder.encode(adminDTO.getNewPassword());
            //4.修改数据库密码
            lambdaUpdateWrapper.eq(Admin::getId, userId).set(Admin::getPassword, newPassword);
            boolean update = update(lambdaUpdateWrapper);
            if (!update) {
                return new ResponseResult(400, "修改密码失败，请联系客服");
            }
            redisTemplate.delete(adminKey);
            return new ResponseResult(200, "修改密码成功", update);
        }
        return ResponseResult.fail();
    }
}

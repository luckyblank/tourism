package com.hzw.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.Message;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.vo.MessageVo;
import com.hzw.tourism.mapper.MessageMapper;
import com.hzw.tourism.qo.MessageQuery;
import com.hzw.tourism.service.AdminService;
import com.hzw.tourism.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hzw.tourism.constant.RedisConstants.CACHE_MESSAGE__KEY;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ArrayList<MessageVo>  getMessage() {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        //父级发表留言
        queryWrapper.isNull("p_id").orderByDesc("ADD_TIME");
        List<Message> commentList = super.list(queryWrapper);
        ArrayList<MessageVo> listMessage = new ArrayList<>();
        //遍历查看发表留言被回复留言的信息
        commentList.forEach(message -> {
            QueryWrapper<Message> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("p_id", message.getId()).orderByDesc("ADD_TIME");
            List<Message> comments = super.list(queryWrapper2);
            ArrayList<MessageVo> commentVos = new ArrayList<>();
            String userNameForm = adminService.getById(message.getUserId()).getUsername();

            comments.forEach(comment1 -> {
                //封装回复留言的客服信息
                commentVos.add(MessageVo.builder()
                        .addUserId(comment1.getAddUserId())
                        .userName(adminService.getById(comment1.getAddUserId()).getUsername()).content(comment1.getContent())
                        .addTime(comment1.getAddTime())
                        .toUserId(message.getUserId())
                        .toUsername(userNameForm).build());
                //封装留言人信息
                listMessage.add(MessageVo.builder()
                        .userId(message.getUserId())
                        .userName(userNameForm)
                        .content(message.getContent())
                        .addTime(message.getAddTime())
                        .commentVo(commentVos).build());
            });
        });
        return listMessage;
    }


    /**
     * 用户
     *
     * @param id
     * @return
     */
    @Override
    public  ArrayList<MessageVo> getMessageUser(Long id) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "ID", id);
        //父级发表留言
        queryWrapper.isNull("p_id").orderByDesc("ADD_TIME");
        List<Message> commentList = super.list(queryWrapper);
        ArrayList<MessageVo> listMessage = new ArrayList<>();
        //遍历查看发表留言被回复留言的信息
        commentList.forEach(message -> {
            QueryWrapper<Message> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("p_id", message.getId()).orderByDesc("ADD_TIME");
            List<Message> comments = super.list(queryWrapper2);
            ArrayList<MessageVo> commentVos = new ArrayList<>();
            String userNameForm = adminService.getById(message.getUserId()).getUsername();
            comments.forEach(comment1 -> {
                //封装回复留言的客服信息
                commentVos.add(MessageVo.builder()
                        .addUserId(comment1.getAddUserId())
                        .userName(adminService.getById(comment1.getAddUserId()).getUsername()).content(comment1.getContent())
                        .addTime(comment1.getAddTime())
                        .toUserId(message.getUserId())
                        .id(comment1.getId())
                        .toUsername(userNameForm).build());
                //封装留言人信息
                listMessage.add(MessageVo.builder()
                        .userId(message.getUserId())
                        .userName(userNameForm)
                        .content(message.getContent())
                        .addTime(message.getAddTime())
                        .id(message.getId())
                        .commentVo(commentVos).build());
            });
        });
        return listMessage;
    }


    @Override
    public Boolean addMessage(Message message) {
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long id = message.getId();
            Admin admin = adminService.getById(userId);
            message.setAddTime(date);
            message.setUserId(userId);
            message.setUserName(admin.getUsername());
            message.setName(admin.getName());
            boolean save = save(message);
            if (save){
                redisTemplate.delete(CACHE_MESSAGE__KEY + id);
            }
            return save;
    }


    @Transactional()
    @Override
    public Boolean reply(Message message) {
        Long userId = WebUtils.getUserId();
            Date date = new Date();
            Admin admin = adminService.getById(userId);
            message.setAddTime(date);
            message.setAddUserId(userId);
            message.setUserName(admin.getUsername());
            message.setName(admin.getName());
            message.setState(2);
            baseMapper.insert(message);
            return  messageMapper.updateStateByPid(message.getPid());

    }


    @Override
    public Message findMessageById(Long id) throws GlobalExceptionMyHandler{
        String key = CACHE_MESSAGE__KEY + id;
        String messageById = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(messageById)) {
            Message message = JSON.parseObject(messageById, Message.class);
            log.info("走缓存.....");
            return message;
        }
        if ("".equals(messageById)) {
            throw new  GlobalExceptionMyHandler("留言不存在");
        }
        Message message = messageMapper.selectById(id);
        if (message == null) {
            redisTemplate.opsForValue().setIfAbsent(key, "", CommonConstants.ONE, TimeUnit.MINUTES);
            throw new  GlobalExceptionMyHandler("留言不存在");
        }
        redisTemplate.opsForValue().set(key, JSON.toJSONString(message), CommonConstants.ONE, TimeUnit.HOURS);
        log.info("存入缓存.....");
        return message;
    }

    @Transactional
    @Override
    public Boolean deleteById(Message message) {
        LambdaQueryWrapper<Message> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Message::getId, message.getId());
        Message message2 = messageMapper.selectOne(queryWrapper1);
        //判断是发表人删留言还是管理员
        if (message2.getPid() != null) {
            //如果是管理员则删除自个留言
            Boolean isSuccess = deleteById(message);
            return isSuccess;
        } else {
            Long id = message.getId();
            String key = CACHE_MESSAGE__KEY + id;
            String message1 = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(message1)) {
                redisTemplate.delete(key);
            }
            //删除发表人留言 子级留言一起删除
            LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Message::getId, message.getId()).or().eq(Message::getPid, message.getId());
            return remove(queryWrapper);
        }
    }


    @Override
    public List<Map<String, Object>> stateAll() {
        Integer count0 = lambdaQuery().eq(Message::getDeleteStatus, 0).eq(Message::getState, 0).count();
        Integer count1 = lambdaQuery().eq(Message::getDeleteStatus, 0).eq(Message::getState, 1).count();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map0 = new HashMap<>();
        map0.put("key", "待回复");
        map0.put("value", count0);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", "已回复");
        map1.put("value", count1);
        mapList.add(map0);
        mapList.add(map1);
        return (mapList);
    }

    //管理员查看所有留言
    @Override
    public IPage<Message> listPage(MessageQuery query) {
        Long userId1 = WebUtils.getUserId();
        //分页
        Page<Message> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getUserName()), Message::getUserName, query.getUserName());
        //lambdaQueryWrapper.eq(Message::getUserId,userId1);
        //分页
        IPage<Message> result = messageMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }


    //用户查看所有留言
    @Override
    public IPage<Message>  listMessage(MessageQuery query) {
        Long userId1 = WebUtils.getUserId();
        //分页
        Page<Message> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(userId1 != null, Message::getUserId, userId1);
        lambdaQueryWrapper.eq(query.getState() != null, Message::getState, query.getState());
        //分页
        IPage<Message> result = messageMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }

    @Override
    public Integer stateReply() {
        Long userId = WebUtils.getUserId();
        Integer count1 = lambdaQuery().eq(Message::getDeleteStatus, 0).eq(Message::getState, 1).eq(Message::getUserId, userId).count();
        return count1;
    }

    @Override
    public  List<Message> listReply(Long pid) {
        //条件查询
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //id=pid
        lambdaQueryWrapper.eq(pid != null, Message::getPid, pid);
        lambdaQueryWrapper.isNotNull(Message::getAddUserId);
        List<Message> messageList = list(lambdaQueryWrapper);
        return Optional.ofNullable(messageList).orElse(Collections.emptyList());
    }


}

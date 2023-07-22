package com.hzw.tourism.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Message;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.qo.MessageQuery;
import com.hzw.tourism.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/message")
public class MessageController {
@Autowired
    private MessageService messageService;


    /**
     * 查看发布留言数量
     * @return
     */

    @GetMapping("/getMessage")
    public ResponseResult getMessage() throws GlobalExceptionHandler {
        return ResponseResult.success(messageService.getMessage());
    }

    @GetMapping("/getMessageUser")
    ResponseResult getMessageUser(Long id) throws GlobalExceptionHandler {
        Assert.notNull(id,"id不能为null");
        return ResponseResult.success(messageService.getMessageUser(id));
    }



        /**
         * 新增留言
         * @param message
         * @return
         */
        @PostMapping("/addMessage")
        public ResponseResult addMessage (@RequestBody Message message) throws GlobalExceptionHandler {

            return ResponseResult.isSuccess(messageService.addMessage(message),"新增留言");
        }

        /**
         * 回复留言
         * @param message
         * @return
         */
        @PostMapping("/reply")
        public ResponseResult reply (@RequestBody Message message) throws GlobalExceptionHandler{
            if (ObjectUtils.isEmpty(message.getPid())){
                return ResponseResult.fail("回复留言失败，pid为null");
            }
            return ResponseResult.isSuccess(messageService.reply(message),"修改回复状态");
        }

        /**
         * 通过id查询留言
         * @param id
         * @return
         */
        @GetMapping("/findMessageById")
        public ResponseResult findMessageById (@RequestParam Long id) throws GlobalExceptionHandler{
            Assert.notNull(id,"id不能为null");
            Message message = messageService.findMessageById(id);
            return ResponseResult.success(message);
        }

        /**
         * 根据id删除留言信息
         * @param message
         * @return
         */
        @PostMapping("/deleteById")
        public ResponseResult deleteById (@RequestBody Message message) throws GlobalExceptionHandler {
            if (ObjectUtils.isEmpty(message)){
                return ResponseResult.fail("请传入message!!");
            }
            return ResponseResult.isSuccess(messageService.deleteById(message),"删除留言");
        }

        /**
         * 查找留言所有状态数量
         * @return
         */
        @GetMapping("/stateAll")
        @PreAuthorize("hasAuthority('system:message:stateAll')")
        public ResponseResult stateAll () throws GlobalExceptionHandler {
          return ResponseResult.success(messageService.stateAll());
        }

        /**
         * 查找留言所有状态数量
         * @return
         */
        @GetMapping("/stateReply")
        public ResponseResult stateReply () throws GlobalExceptionHandler{
            return ResponseResult.success(messageService.stateReply());

        }

        /**
         * 分页查询留言-管理员
         * @param query
         * @return
         */
        @PostMapping("/listPage")
        public ResponseResult listPage (@RequestBody MessageQuery query) throws GlobalExceptionHandler {
            IPage<Message> result = messageService.listPage(query);
            if (CollectionUtils.isEmpty(result.getRecords())){
                return ResponseResult.success(Collections.emptyList());
            }
            return ResponseResult.success(result.getRecords(),result.getTotal());
        }

        /**
         * 分页查询用户留言
         * @param query
         * @return
         */
        @PostMapping("/listMessage")
        public ResponseResult listMessage (@RequestBody MessageQuery query)  throws GlobalExceptionHandler
        {
            IPage<Message> result = messageService.listMessage(query);
            if (CollectionUtils.isEmpty(result.getRecords())){
                return ResponseResult.success(Collections.emptyList());
            }
            return ResponseResult.success(result.getRecords(),result.getTotal());
        }

    @GetMapping("/listReply")
    public ResponseResult listReply(Long pid) throws GlobalExceptionHandler {
            Assert.notNull(pid,"pid不能为null");
            return ResponseResult.success( messageService.listReply(pid));
    }

    }


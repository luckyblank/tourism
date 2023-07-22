package com.hzw.tourism.service;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-01-22
 */
public interface CommentService extends IService<Comment> {

    ResponseResult getComment();
    public ResponseResult publisher(Comment comment);
    /**
     * 查找所有评论
     * @return
     */
    public ResponseResult comments(Comment comment);

    ResponseResult getcommentsByPid(Long id);

    ResponseResult detete(Comment comment);

    ResponseResult getCommentAll(Long foreignId);
}

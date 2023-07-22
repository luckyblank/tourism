package com.hzw.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.Comment;
import com.hzw.tourism.service.CommentService;
import com.hzw.tourism.vo.CommentVo;
import com.hzw.tourism.mapper.CommentMapper;
import com.hzw.tourism.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-22
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AdminService adminService;

    @Override
    public ResponseResult getComment() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id", 0).orderByDesc("create_time");
        List<Comment> commentList = super.list(queryWrapper);
        ArrayList<CommentVo> listComment = new ArrayList<>();
        commentList.forEach(comment -> {
            QueryWrapper<Comment> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("p_id", comment.getId()).orderByDesc("create_time");
            //子级
            List<Comment> comments = super.list(queryWrapper2);
            log.info("comments:{}", comment);
            ArrayList<CommentVo> commentVos = new ArrayList<>();
            String userNameForm = adminService.getById(comment.getUserId()).getUsername();
            if (comments.size() == 0) {
                listComment.add(CommentVo.builder()
                        .userId(comment.getUserId())
                        .username(userNameForm)
                        .content(comment.getContent())
                        .createTime(comment.getCreateTime())
                        .commentVo(commentVos).build());
            }
            comments.forEach(comment1 -> {
                //封装评论列表
                commentVos.add(CommentVo.builder().userId(comment1.getUserId())
                        .username(adminService.getById(comment1.getUserId())
                                .getUsername()).content(comment1.getContent())
                        .createTime(comment1.getCreateTime()).toUserId(comment.getUserId())
                        .toUsername(userNameForm).build());


            });
            listComment.add(CommentVo.builder()
                    .userId(comment.getUserId())
                    .username(userNameForm)
                    .content(comment.getContent())
                    .createTime(comment.getCreateTime())
                    .commentVo(commentVos).build());
        });
        return ResponseResult.success(listComment);

    }

    @Override
    public ResponseResult getCommentAll(Long foreignId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(foreignId != null, "foreign_id", foreignId)
                .eq("p_id", 0).orderByAsc("create_time");
        List<Comment> commentList = super.list(queryWrapper);
        ArrayList<CommentVo> listComment = new ArrayList<>();
        commentList.forEach(comment -> {
            QueryWrapper<Comment> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("p_id", comment.getId()).orderByAsc("create_time");

            List<Comment> comments = super.list(queryWrapper2);
            ArrayList<CommentVo> commentVos = new ArrayList<>();
            String userNameForm = adminService.getById(comment.getUserId()).getUsername();

            comments.forEach(comment1 -> {
                //封装评论列表
                commentVos.add(CommentVo.builder()
                        .userId(comment1.getUserId())
                        .username(adminService.getById(comment1.getUserId()).getUsername())
                        .content(comment1.getContent())
                        .createTime(comment1.getCreateTime())
                        .toUserId(comment.getUserId())
                        .foreignId(comment1.getForeignId())
                        .id(comment1.getId())
                        .pid(comment1.getPid())
                        .icon(comment1.getIcon())
                        .toUsername(userNameForm).build());
            });

            listComment.add(CommentVo.builder()
                    //   .foreignId(comment.getForeignId())
                    .userId(comment.getUserId())
                    .username(userNameForm)
                    .content(comment.getContent())
                    .createTime(comment.getCreateTime())
                    .id(comment.getId())
                    .pid(comment.getPid())
                    .icon(comment.getIcon())
                    .commentVo(commentVos).build());
        });
        return ResponseResult.success(listComment);

    }


    @Override
    public ResponseResult publisher(Comment comment) {
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Admin admin = adminService.getById(userId);
        comment.setCreateTime(date);
        comment.setUserId(userId);
        comment.setIcon(admin.getIcon());
        int insert = baseMapper.insert(comment);
        if (insert == 0) {
            return ResponseResult.fail();
        }
        return new ResponseResult(200, "发表成功");

    }

    @Override
    public ResponseResult comments(Comment comment) {
        Long userId = WebUtils.getUserId();
        System.err.println(comment);
        if (comment.getPid() != 0) {
            Admin user = adminService.getById(userId);
            Date date = new Date();
            //封装评论人信息
            comment.setCreateTime(date);
            comment.setUserId(userId);
            comment.setIcon(user.getIcon());
            int insert = baseMapper.insert(comment);
            if (insert == 0) {
                return ResponseResult.fail();
            }
            return new ResponseResult(200, "评论成功");
        }
        return ResponseResult.fail();
    }

    @Override
    public ResponseResult getcommentsByPid(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getPid, id);
        List<Comment> comments = baseMapper.selectList(queryWrapper);
        return ResponseResult.success(comments);
    }

    @Override
    public ResponseResult detete(Comment comment) {
        Long userId = WebUtils.getUserId();
        if (comment == null) {
            return ResponseResult.fail();
        }
//        LambdaQueryWrapper<Comment> queryWrapper1 = new LambdaQueryWrapper<>();
//        queryWrapper1.eq(Comment::getId,comment.getId());
        Comment comment2 = getById(comment.getId());
        // Comment comment1 = commentMapper.selectOne(queryWrapper1);
        if (comment2 == null) {
            return ResponseResult.fail();
        }
        if (comment2.getPid() != 0) {
            int delete = commentMapper.deleteById(comment.getId());
            if (delete == 0) {
                return new ResponseResult(400, "删除失败");
            }
            return new ResponseResult(200, "删除成功");
        } else {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getId, comment.getId()).eq(Comment::getUserId, userId);
            boolean remove = remove(queryWrapper);
            if (remove) {
                LambdaQueryWrapper<Comment> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Comment::getPid, comment.getId());
                remove(queryWrapper1);
                return new ResponseResult(200, "删除成功");
            }
            return new ResponseResult(400, "删除失败");
        }
    }

}

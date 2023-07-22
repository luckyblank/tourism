package com.hzw.tourism.controller;


import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Comment;
import com.hzw.tourism.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-22
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
@Autowired
    private CommentService commentService;

    /**
     *
     * 获取所有评论列表-父级-子级
     * @return
     */
    @GetMapping("/getComment")
    public ResponseResult  getComment(){
    return commentService.getComment();

}

    /**
     *
     * 获取模块业务所有评论列表-父级-子级
     * @return
     */
    @GetMapping("/getCommentAll")
    public ResponseResult  getCommentAll(Long foreignId){
        return commentService.getCommentAll(foreignId);

    }
    /**
     * 发表评论
     * @param comment
     * @return
     */
    @PostMapping("/publisher")
    public ResponseResult publisher(@RequestBody Comment comment){
    return commentService.publisher(comment);
    }

    /**
     * 评论
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public ResponseResult comments(@RequestBody Comment comment){
        return commentService.comments(comment);
    }

    /**
     * 获取所有子评论
     * @param id
     * @return
     */
    @GetMapping("/getcommentsByPid")
    public ResponseResult getcommentsByPid(Long id){
        return commentService.getcommentsByPid(id);
    }

    @PostMapping("/detete")
    public ResponseResult detete(@RequestBody Comment comment){
        return commentService.detete(comment);
    }












































































}

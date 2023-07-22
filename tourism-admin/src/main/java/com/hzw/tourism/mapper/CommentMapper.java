package com.hzw.tourism.mapper;

import com.hzw.tourism.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzw.tourism.vo.CommentVo;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzw
 * @since 2023-01-22
 */
public interface CommentMapper extends BaseMapper<Comment> {

    //一级评论


    public List<CommentVo> getCommentAll();

    @Insert("INSERT INTO COMMENT (content, user_id,p_id) VALUES ( #{content},#{userId},#{pId})")
   public boolean comments(Comment comment);



}

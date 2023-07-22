package com.hzw.tourism.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVo {
    /**
     * 评论 一级评论用户
     */
    private Long userId;
    private String username;
    private String content; //发表内容
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;


    /**
     * 二级评论回复谁
     */
    private Long toUserId;
    private String toUsername;
    /**
     * 二级评论
     */
    private List<CommentVo> commentVo=new ArrayList<>();

    @ApiModelProperty(value = "业务模块id")
    private Long foreignId;
    @ApiModelProperty(value = "父级评论id")
    private Long pid;
    private Long id;
    private String icon;



}

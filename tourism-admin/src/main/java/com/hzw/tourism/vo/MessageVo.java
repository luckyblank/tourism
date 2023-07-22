package com.hzw.tourism.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MessageVo {
    /**
     * 客服id
     */
    private Long addUserId;
    /**
     * 评论 一级用户留言内容
     */
    private Long userId;
    private String userName;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date addTime;
    private String content;  //内容

    /**
     * 二级评论回复谁
     */
    private Long toUserId;
    private String toUsername;
    /**
     * 二级评论
     */
    private List<MessageVo> commentVo=new ArrayList<>();
/**
 * 留言id
 */
private Long id;

}

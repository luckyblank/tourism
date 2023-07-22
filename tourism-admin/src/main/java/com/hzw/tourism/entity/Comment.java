package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzw
 * @since 2023-01-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @TableField("p_id")
    @ApiModelProperty(value = "父级评论id")
    private Long pid;

    @ApiModelProperty(value = "业务模块id")
    private Long foreignId;

    @ApiModelProperty(value = "回复对象")
    private String target;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @TableLogic
    private Integer deleteState;
    private String icon;




}

package com.hzw.tourism.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "添加人ID")
    @TableField("ADD_USER_ID")
    private Long addUserId;
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("ADD_TIME")
    private Date addTime;

    @ApiModelProperty(value = "删除标志")
    @TableField("DELETE_STATUS")
    @TableLogic
    private Integer deleteStatus;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("USER_NAME")
    private String userName;
    @ApiModelProperty(value = "真实姓名")
    @TableField("NAME")
    private String name;
    @ApiModelProperty(value = "内容")
    @TableField("CONTENT")
    private String content;
    @ApiModelProperty(value = "状态 0待回复 1已回复")
    @TableField("STATE")
    private Integer state;
    @TableField("p_id")
    @ApiModelProperty(value = "父级评论id")
    private Long pid;


}

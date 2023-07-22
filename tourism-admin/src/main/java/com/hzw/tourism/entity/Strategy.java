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
@ApiModel(value="Strategy对象", description="")
public class Strategy implements Serializable {

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

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("MODIFY_TIME")
    private Date modifyTime;
    @ApiModelProperty(value = "logo图片地址")
    @TableField("IMG_URL")
    private String imgUrl;
    @ApiModelProperty(value = "主题")
    @TableField("TITLE")
    private String title;
    @ApiModelProperty(value = "等级")
    @TableField("RATING")
    private Integer rating;
    @ApiModelProperty(value = "简介")
    @TableField("SUMMARY")
    private String summary;
    @ApiModelProperty(value = "内容图片地址")
    @TableField("INTRO_URL")
    private String introUrl;
    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private Integer state;
    private Integer liked;
    private Integer comments;
    private Integer collects;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private Boolean isLike;
    @TableField(exist = false)
    private String icon;
    @TableField(exist = false)
    private Boolean isCollect;
    private Integer productType;
}

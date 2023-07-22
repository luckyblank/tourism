package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="TRoute1对象", description="")
@TableName("t_routel")
public class Routel implements Serializable {

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

    @ApiModelProperty(value = "修改人ID")
    @TableField("MODIFY_USER_ID")
    private Long modifyUserId;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @ApiModelProperty(value = "标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "出发地点")
    @TableField("START_SITE")
    private String startSite;

    @ApiModelProperty(value = "结束地点")
    @TableField("END_SITE")
    private String endSite;

    @ApiModelProperty(value = "结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("END_TIME")
    private Date endTime;

    @ApiModelProperty(value = "出团日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("START_TIME")
    private Date startTime;

    @ApiModelProperty(value = "持续天数")
    @TableField("DAY")
    private Integer day;

    @ApiModelProperty(value = "产品编号")
    @TableField("PRODUCT_CODE")
    private String productCode;

    @ApiModelProperty(value = "价格")
    @TableField("PRICE")
    private Double price;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "图片")
    @TableField("IMG_URL")
    private String imgUrl;

    @ApiModelProperty(value = "介绍")
    @TableField("INTRO")
    private String intro;


}

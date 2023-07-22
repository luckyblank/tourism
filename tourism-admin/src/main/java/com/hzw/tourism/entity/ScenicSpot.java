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
@ApiModel(value="ScenicSpot对象", description="")
@TableName("scenic_spot")
public class ScenicSpot implements Serializable {

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

    @ApiModelProperty(value = "景点名称")
    @TableField("SPOT_NAME")
    private String spotName;

    @ApiModelProperty(value = "景点简介")
    @TableField("SPOT_INTRO")
    private String spotIntro;

    @ApiModelProperty(value = "景点星级")
    @TableField("SPOT_STAR")
    private Integer spotStar;

    @ApiModelProperty(value = "景点地址")
    @TableField("SPOT_ADDRESS")
    private String spotAddress;

    @ApiModelProperty(value = "开放时间")
    @TableField("OPEN_TIME")
    private String openTime;

    @ApiModelProperty(value = "门票")
    @TableField("TICKETS_MESSAGE")
    private Double ticketsMessage;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "图片")
    @TableField("IMG_URL")
    private String imgUrl;


}

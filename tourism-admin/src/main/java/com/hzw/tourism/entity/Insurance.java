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
@ApiModel(value="Tb_Insurance对象", description="")
@TableName("tb_insurance")
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "添加人id")
    @TableField("ADD_USER_ID")
    private Long addUserId;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("ADD_TIME")
    private Date addTime;

    @ApiModelProperty(value = "0为未删除")
    @TableLogic
    @TableField("DELETE_STATUS")
    private Integer deleteStatus;

    @ApiModelProperty(value = "修改人id")
    @TableField("MODIFY_USER_ID")
    private Long modifyUserId;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @ApiModelProperty(value = "标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "保险公司")
    @TableField("INSURANCE_COMPANY")
    private Integer insuranceCompany;

    @ApiModelProperty(value = "价格")
    @TableField("PRICE")
    private Double price;

    @ApiModelProperty(value = "保险类型")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "保险介绍")
    @TableField("RESUME")
    private String resume;

    @ApiModelProperty(value = "保险状态 0注销 1 发布 2待发布")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "保险图片")
    @TableField("IMG_URL")
    private String imgUrl;

    @TableField(exist = false)
    private String icCode;

    @TableField(exist = false)
    private String name;
}

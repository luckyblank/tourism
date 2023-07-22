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
 * @since 2023-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TOrder对象", description="")
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID")
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

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("USER_NAME")
    private String userName;
    @TableField(exist = false)
    private String name;

    @ApiModelProperty(value = "产品ID")
    @TableField("PRODUCT_ID")
    private Long productId;

    @ApiModelProperty(value = "产品名称")
    @TableField("PRODUCT_NAME")
    private String productName;

    @ApiModelProperty(value = "订单费用")
    @TableField("FEE")
    private Double fee;

    @ApiModelProperty(value = "产品类型0车票1酒店2路线3景点4保险")
    @TableField("PRODUCT_TYPE")
    private Integer productType;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "订单编号")
    @TableField("ORDER_CODE")
    private String orderCode;

    @ApiModelProperty(value = "订单日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("ORDER_TIME")
    private Date orderTime;

    @ApiModelProperty(value = "预定出发日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("SETOFF_TIME")
    private Date setoffTime;

    @ApiModelProperty(value = "联系电话")
    @TableField("LINK_TEL")
    private String linkTel;

    @ApiModelProperty(value = "人数")
    @TableField("PEOPLE_COUNT")
    private Integer peopleCount;

    @ApiModelProperty(value = "特殊要求")
    @TableField("REQUIREMENT")
    private String requirement;

    @ApiModelProperty(value = "身份证号码")
    @TableField("IC_CODE")
    private String icCode;

    @TableField("IMG_URL")
    private String imgUrl;

    @ApiModelProperty(value = "退款日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("refund_time")
    private Date refundTime;

    @ApiModelProperty(value = "支付日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("SETOFF_TIME")
    private Date pay_time;
    private Integer day;




}

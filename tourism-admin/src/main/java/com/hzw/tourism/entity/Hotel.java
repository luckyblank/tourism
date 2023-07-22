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
 * @since 2023-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbHotel对象", description="")
@TableName("tb_hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "添加人ID")
    @TableField("ADD_USER_ID")
    private Long addUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    @TableField("ADD_TIME")
    private Date addTime;

    @ApiModelProperty(value = "删除标志")
    @TableField("DELETE_STATUS")
    @TableLogic
    private Integer deleteStatus;

    @ApiModelProperty(value = "修改人ID")
    @TableField("MODIFY_USER_ID")
    private Long modifyUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @ApiModelProperty(value = "酒店名称")
    @TableField("HOTEL_NAME")
    private String hotelName;

    @ApiModelProperty(value = "酒店简介")
    @TableField("HOTEL_INTRO")
    private String hotelIntro;

    @ApiModelProperty(value = "酒店星级")
    @TableField("HOTEL_STAR")
    private Integer hotelStar;

    @ApiModelProperty(value = "联系方式")
    @TableField("LINK_PHONE")
    private String linkPhone;

    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "状态 0发布 1待发布")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "图片")
    @TableField("IMG_URL")
    private String imgUrl;

    @ApiModelProperty(value = "价格")
    @TableField("PRICE")
    private Double price;

    @ApiModelProperty(value = "房间数")
    @TableField("HOTEL_NUM")
    private Integer hotelNum;

    @ApiModelProperty(value = "房型信息")
    @TableField("Room_Type_Information")
    private String roomTypeInformation;

    @ApiModelProperty(value = "设施信息")
    @TableField("Facility_Information")
    private String facilityInformation;

    @ApiModelProperty(value = "订房须知")
    @TableField("Booking_Instructions")
    private String bookingInstructions;

    @ApiModelProperty(value = "景区附近酒店")
    @TableField("Scenic_Spot_Id")
    private Long scenicSpotId;
    @ApiModelProperty(value = "0：民宿 1：酒店 2：公寓")
    @TableField("TYPE")
    private Integer type;
    @TableField("STAY_DAY")
    private Integer stayDay;
//入住时间
@DateTimeFormat(pattern = "yyyy-MM-dd")
@JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date checkInTime;
//离店时间
@DateTimeFormat(pattern = "yyyy-MM-dd")
@JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date checkOutTime;



}

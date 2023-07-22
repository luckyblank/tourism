package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@ApiModel(value="TbCar对象", description="")
@TableName("tb_car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "添加人ID")
    @TableField("ADD_USER_ID")
    private Long addUserId;

    @ApiModelProperty(value = "添加时间")
    @TableField("ADD_TIME")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date addTime;

    @ApiModelProperty(value = "删除标志 1")
    @TableField("DELETE_STATUS")
    @TableLogic
    private Integer deleteStatus;

    @ApiModelProperty(value = "修改人ID")
    @TableField("MODIFY_USER_ID")
    private Long modifyUserId;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date modifyTime;

    @ApiModelProperty(value = "车票标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "出发地点")
    @TableField("START_PLACE")
    private String startPlace;

    @ApiModelProperty(value = "到达地点")
    @TableField("END_PLACE")
    private String endPlace;

    @ApiModelProperty(value = "出发日期")
    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "开点时间段")
    @TableField("Time_Range")
    private String timeRange;

    @ApiModelProperty(value = "需要时间")
    @TableField("NEED_TIME")
    private String needTime;

    @ApiModelProperty(value = "上车集中地")
    @TableField("GATHER_PLACE")
    private String gatherPlace;

    @ApiModelProperty(value = "车的类型，0是飞机，1是高铁，2是火车")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "经停站")
    @TableField("Intermediate_Stop")
    private String intermediateStop;

    @ApiModelProperty(value = "图片")
    @TableField("IMG_URL")
    private String imgUrl;

    @ApiModelProperty(value = "状态 0注销，1发布，2待发布")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    @ApiModelProperty(value = "价格")
    @TableField("PRICE")
    private Double price;
    @ApiModelProperty(value = "换乘时间")
    @TableField("Intermediate_Time")
    private String intermediateTime;
    @ApiModelProperty(value = "换乘站")
    @TableField("Interchange_Station")
    private String interchangeStation;
    private String trainNumber;
    private String trainName;
    private String timeEnd;
}

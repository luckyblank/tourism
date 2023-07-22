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

/**
 * <p>
 * 
 * </p>
 *
 * @author hzw
 * @since 2023-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Collection对象", description="")
@TableName("t_collection")
public class CustomCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("Product_Id")
    private Long productId;
    @TableField("USER_ID")
    private Long userId;
    @TableField("ADD_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date addTime;
    @TableField("Product_Type")
    private Integer productType;
    private String title;
    private Integer collections;
   // private Integer deleteState;





}

package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

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
 * @since 2023-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TInsuranceUserInfo对象", description="")
@TableName("t_insurance_user_info")
public class InsuranceUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "保险id")
    private Long insuranceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTiem;
    @TableLogic
    private Integer deleteState;
    @ApiModelProperty(value = "投保人")
    private Long userId;
    @ApiModelProperty(value = "投保人姓名")
    private String userName;
    @ApiModelProperty(value = "身份证")
    private String icCode;
    private Integer type;
    private Integer insuranceCompany;


}

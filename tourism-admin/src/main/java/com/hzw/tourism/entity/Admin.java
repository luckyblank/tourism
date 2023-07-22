package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_admin")
public class Admin implements Serializable {
    private static final Long seralVersionUD=-22178712782L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Integer state;
    @TableField("user_type")
    private Integer userType;
    @TableField("create_by")
    private Long createBy;
    @TableField("update_by")
    private Long updateBy;
    @TableField("delete_status")
    @TableLogic
    private Integer deleteStatus;
    private String name;
    @TableField("link_tel")
    private String linkTel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("modify_time")
    private Date modifyTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("add_time")
    private Date addTime;
    @TableField("ic_code")
    private String icCode;
    private String icon;
    private Long province;
    private String email;
}

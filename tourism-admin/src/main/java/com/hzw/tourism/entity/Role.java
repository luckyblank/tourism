package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_role")
public class Role {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("role_code")
    private String roleCode;
    /**
     * 0开启，1关闭
     */
    private Long status;
    @TableLogic
    private Long del;
    @TableField("create_by")
    private String createBy;
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private String remark;


}

package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_role_menu")
public class RoleMenu {
    @TableField("role_id")
    private Long roleId;
    @TableField("menu_id")
    private Long menuId;
    @TableField("role_menu_code")
    private String roleMenuCode;
    @TableField("is_accredit")
    private Integer isAccredit;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableLogic
    @TableField("is_deleted")
    private Long isDeleted;


}

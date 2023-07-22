package com.hzw.tourism.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu implements Serializable {
    private static final Long seralVersionUD=-22178712782L;
    @TableId
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    @TableField("menu_name")
    private String menuName;
    private String path;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_by")
    private Integer createBy;
    @TableField("update_by")
    private Integer updateBy;
    private Integer del;
    private String remark;

}

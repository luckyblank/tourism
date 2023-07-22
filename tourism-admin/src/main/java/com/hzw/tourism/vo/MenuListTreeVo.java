package com.hzw.tourism.vo;
import com.hzw.tourism.entity.Menu;
import com.hzw.tourism.utils.TreeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuListTreeVo extends TreeNodeVo<MenuListTreeVo> {
    private Long id;
    private Long parentId;
    @ApiModelProperty("权限菜单名")
    private String menuName;
    @ApiModelProperty("是否开启，0开启，1关闭")
    private String visible;
    @ApiModelProperty("状态，0上线，1下线")
    private String status;
    @ApiModelProperty("权限码")
    private String perms;
    private Date createTime;
    private Date updateTime;
    private Integer createBy;
    private Integer updateBy;
    private Integer del;
    private String remark;

    public static List<MenuListTreeVo> createTree(List<Menu> menus, Long root){
        ArrayList<MenuListTreeVo> treeVos = new ArrayList<>();
        //MenuListTreeVo node=null;
        for (Menu menu : menus) {
            MenuListTreeVo node = new MenuListTreeVo();
            BeanUtils.copyProperties(menu,node);
            treeVos.add(node);
        }
        return TreeUtil.bulidTree(treeVos,root);
    }
}
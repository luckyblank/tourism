package com.hzw.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.Menu;
import com.hzw.tourism.entity.RoleMenu;
import com.hzw.tourism.mapper.MenuMapper;
import com.hzw.tourism.service.MenuService;
import com.hzw.tourism.service.RoleMenuService;
import com.hzw.tourism.utils.TreeUtil;
import com.hzw.tourism.vo.MenuListTreeVo;
import com.hzw.tourism.vo.MenuListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohuang
 * @date 2023/6/26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final static  Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Transactional
    @Override
    public MenuListVo selectList(Long roleId) throws Exception {
        ArrayList<MenuListTreeVo> treeVos = new ArrayList<>();
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getDel, 0);
        List<Menu> menuList = list(queryWrapper);
        if (CollectionUtils.isEmpty(menuList)) {
            List<MenuListTreeVo> listTreeVos = TreeUtil.bulidTree(treeVos, CommonConstants.ROOT);
            return new MenuListVo(listTreeVos, Collections.emptyList(), Collections.emptyList());
        }
        for (Menu menu : menuList) {
            MenuListTreeVo menuTreeVo = new MenuListTreeVo();
            BeanUtils.copyProperties(menu, menuTreeVo);
            treeVos.add(menuTreeVo);
        }
        List<MenuListTreeVo> menuListTreeVos = TreeUtil.bulidTree(treeVos, CommonConstants.ROOT);
        //根据角色id查询已授权的menuId列表
        List<RoleMenu> frontendPermissionsList = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>().select(RoleMenu::getMenuId).eq(RoleMenu::getRoleId, roleId));
        List<Long> menuIds = frontendPermissionsList.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());

        MenuListVo menuListVo = new MenuListVo();
        menuListVo.setMenuList(menuListTreeVos);
        menuListVo.setCheckList(menuIds);

    return menuListVo;
}
}

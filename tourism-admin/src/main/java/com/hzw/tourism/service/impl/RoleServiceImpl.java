package com.hzw.tourism.service.impl;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.DTO.RoleMenuAddDTO;
import com.hzw.tourism.DTO.UserRoleAddVo;
import com.hzw.tourism.DTO.UserRoleDTO;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.Role;
import com.hzw.tourism.entity.RoleMenu;
import com.hzw.tourism.entity.UserRole;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.mapper.AdminMapper;
import com.hzw.tourism.mapper.RoleMapper;
import com.hzw.tourism.mapper.RoleMenuMapper;
import com.hzw.tourism.mapper.UserRoleMapper;
import com.hzw.tourism.qo.RoleQuery;
import com.hzw.tourism.qo.RoleUserListQuery;
import com.hzw.tourism.service.RoleMenuService;
import com.hzw.tourism.service.RoleService;
import com.hzw.tourism.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private static  final Logger logger= LoggerFactory.getLogger(RoleServiceImpl.class);
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public Role getRole(Long id) throws GlobalExceptionHandler {
        return getById(id);
    }

    @Transactional()
    @Override
    public void delete(Long id) throws GlobalExceptionHandler {
        //1.删除角色
        removeById(id);
        //2.删除关联的角色用户表
        // 2.1. 根据角色ID查询关联的角色用户记录
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId,id);
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        LambdaQueryWrapper<UserRole> queryWrapper1 = new LambdaQueryWrapper<>();
        // 2.2. 遍历查询结果，逐条删除角色用户记录
        if (CollectionUtil.isNotEmpty(userRoles)){
            for (UserRole userRole : userRoles) {
                queryWrapper1.eq(UserRole::getUserRoleCode, userRole.getUserRoleCode());
                userRoleMapper.delete(queryWrapper1);
            }
        }
        //3.删除授权信息
        // 3.1. 根据角色ID查询关联的角色菜单记录
        LambdaQueryWrapper<RoleMenu> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(RoleMenu::getRoleId,id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper2);
        LambdaQueryWrapper<RoleMenu> queryWrapper3 = new LambdaQueryWrapper<>();
        // 3.2. 遍历查询结果，逐条删除角色菜单记录
        if (CollectionUtil.isNotEmpty(roleMenus)){
            for (RoleMenu roleMenu : roleMenus) {
                queryWrapper3.eq(RoleMenu::getRoleMenuCode, roleMenu.getRoleMenuCode());
                roleMenuMapper.delete(queryWrapper3);
            }
        }


    }

    @Override
    public Boolean addOrUpdateRole(Role role) throws Exception {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Role> queryWrapper1 = queryWrapper.eq(StringUtils.isNotBlank(role.getName()), Role::getName, role.getName());
        Role role1 = getOne(queryWrapper1);
        if (ObjectUtils.isNotEmpty(role1)){
            throw new Exception("改角色名已存在！！");
        }
        Date date = new Date();
        Admin user = WebUtils.getUser();
        Boolean isSuccess=false;
        if (role.getId()!=null){
            role.setUpdateBy(user.getUsername());
            role.setUpdateTime(date);
             isSuccess = updateById(role);
        }else {
             role.setRoleCode(WebUtils.getCode());
             role.setCreateBy(user.getUsername());
             role.setCreateTime(date);
             role.setDel(0L);
             role.setStatus(0L);
             isSuccess = save(role);
        }
        return isSuccess;

    }

    @Override
    public Boolean updateState(Role role) throws GlobalExceptionHandler {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",role.getStatus()).eq("id",role.getId());
        boolean isSuccess = update(updateWrapper);
        return isSuccess;
    }

    @Override
    public Boolean associatedUser(UserRoleAddVo userRoleAddVo) throws Exception{
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(userRoleAddVo.getRoleId()),UserRole::getRoleId,userRoleAddVo.getRoleId());
        queryWrapper.eq(ObjectUtils.isNotEmpty(userRoleAddVo.getUserId()),UserRole::getUserId,userRoleAddVo.getUserId());
        UserRole userRole1 = userRoleMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(userRole1)){
            throw new Exception("该角色已关联该用户");
        }

        Admin admin = adminMapper.selectById(userRoleAddVo.getUserId());
        UserRole userRole = new UserRole();
        userRole.setRoleId(userRoleAddVo.getRoleId());
        userRole.setUserId(userRoleAddVo.getUserId());
        userRole.setUserName(admin.getUsername());
        userRole.setAddAdmin(WebUtils.getUser().getUsername());
        userRole.setDel(0L);
        userRole.setCreateTime(new Date());
        userRole.setUserRoleCode(WebUtils.getCode());
        int insert = userRoleMapper.insert(userRole);
        return insert==1?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Boolean deleteAssociated(UserRoleDTO userRoleDTO) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(userRoleDTO.getRoleId()),UserRole::getRoleId,userRoleDTO.getRoleId());
        queryWrapper.eq(ObjectUtils.isNotEmpty(userRoleDTO.getUserId()),UserRole::getUserId,userRoleDTO.getUserId());
        int delete = userRoleMapper.delete(queryWrapper);
        return delete==1?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public IPage<Role> list(RoleQuery roleQuery) {
        Page<Role> page = new Page<>(roleQuery.getCurrentPage(), roleQuery.getPageSize());
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
        query.eq(StringUtils.isNotBlank(roleQuery.getName()),Role::getName,roleQuery.getName());
        Page<Role> result = roleMapper.selectPage(page, query);
        return result;
    }

    @Override
    public IPage<UserRole> roleUserList(RoleUserListQuery query) throws GlobalExceptionHandler {
        Page<UserRole> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(query.getRoleId()),UserRole::getRoleId,query.getRoleId());
        Page<UserRole> result = userRoleMapper.selectPage(page, queryWrapper);
        logger.info("list:{}",result.getRecords());
        return result;
    }

    @Override
    public void volumeLicensing(RoleMenuAddDTO roleMenuAddDTO) throws GlobalExceptionHandler {
        // 1. 根据角色id获取已授权列表
        List<RoleMenu> frontendPermissionsList = roleMenuService.list(new QueryWrapper<RoleMenu>().eq("role_id", roleMenuAddDTO.getRoleId()));
        List<Long> menuIdList = frontendPermissionsList.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());
        List<Long> menuIds = roleMenuAddDTO.getMenuIds();
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleMenu::getMenuId,menuIdList).eq(RoleMenu::getRoleId,roleMenuAddDTO.getRoleId());
        //3.删除已授权列表
        roleMenuService.remove(queryWrapper);
        // 2. 获取传入的授权列表
        List<Long> menuIds1 = roleMenuAddDTO.getMenuIds();
        //4.批量新增
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleMenuAddDTO.getRoleId());
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleMenuCode(WebUtils.getCode());
                    roleMenu.setIsAccredit(0);
                    roleMenu.setIsDeleted(0L);
                    roleMenu.setCreateBy(WebUtils.getUser().getUsername());
                    roleMenu.setCreateTime(new Date());
                    roleMenus.add(roleMenu);
        }
        roleMenuService.saveBatch(roleMenus);

    }


    
}

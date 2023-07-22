package com.hzw.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.DTO.AdminDTO;
import com.hzw.tourism.entity.Province;
import com.hzw.tourism.qo.AdminQuery;
import com.hzw.tourism.qo.UserQuery;

import java.util.List;
import java.util.Map;

public interface AdminService extends IService<Admin> {
    /**
     * 获取不同地区注册用户数量
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> findProvinceByid() throws Exception;

    /**
     * 获取所有的省份
     * @return
     */
    List<Province> findfindProvinceAll() ;

    /**
     * 新增用户
     * @param admin
     * @throws Exception
     */
    void saveOrUpdateUser(Admin admin) throws Exception;

    /**
     * 查找管理员
     * @param id
     * @return
     */
    ResponseResult findAdminById(Long id);

    /**
     * 删除用户
     * @param id
     * @throws Exception
     */
    void deleteById(Long id) throws Exception;

    /**
     * 修改状态
     * @param admin
     * @throws Exception
     */
    void updateStates(Admin admin) throws Exception;

    /**
     * 获取所有状态
     * @return
     */
    List<Map<String, Object>> stateAll() throws Exception;

    /**
     * 用户分页查询
     * @param query
     * @return
     */
    IPage<Admin> listPage(UserQuery query) throws Exception;

    /**
     * 获取在线用户
     * @return
     */
    Integer count1() throws Exception;

    /**
     * 管理员分页查询
     * @param query
     * @return
     */
    IPage<Admin> listPageAdmin(AdminQuery query) throws Exception;

    /**
     * 修改密码
     * @param adminDTO
     * @return
     */
    ResponseResult updatePassword(AdminDTO adminDTO);

    /**
     * 修改用户信息
     * @param admin
     * @throws Exception
     */
    void updateUser(Admin admin) throws Exception;

    /**
     * 查找用户
     * @return
     */
    ResponseResult findUsersById();

    /**
     * 查找管理员
     * @return
     */
    ResponseResult findAdmin();

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    ResponseResult saveOrUpdateAdmin(Admin admin);

    /**
     * 修改管理员
     * @param admin
     * @return
     */
    void updateAdmin(Admin admin) throws Exception;
}

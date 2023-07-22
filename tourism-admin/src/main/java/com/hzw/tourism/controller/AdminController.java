package com.hzw.tourism.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.DTO.AdminDTO;
import com.hzw.tourism.entity.Province;
import com.hzw.tourism.qo.AdminQuery;
import com.hzw.tourism.qo.UserQuery;
import com.hzw.tourism.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "security-测试接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final static Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/updatePassword")
    @ApiOperation("条件查询+分页获取用户信息")
    public ResponseResult updatePassword(@RequestBody AdminDTO adminDTO) {
        return adminService.updatePassword(adminDTO);

    }


    @GetMapping("/count1")
    public Integer count1() throws Exception {
        return adminService.count1();
    }


    @GetMapping("/findProvinceByid")
//    @PreAuthorize("hasAuthority('system:admin:findProvinceByid')")
    public ResponseResult findProvinceByid() {
        try {
            return ResponseResult.success(adminService.findProvinceByid());
        } catch (Exception e) {
            log.error("根据id获取省份列表失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }


    @GetMapping("/findProvinceAll")
    public List<Province> findfindProvinceAll(){
    return adminService.findfindProvinceAll();
    }


    @PostMapping("/saveOrUpdateUser")
    public ResponseResult saveOrUpdateUser(@RequestBody Admin admin) {
        if (ObjectUtils.isEmpty(admin)) {
            return ResponseResult.fail("请传入参数");
        }
        try {
            adminService.saveOrUpdateUser(admin);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error("新增或修改失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/updateUser")
    public ResponseResult updateUser(@RequestBody Admin admin) {
        if (ObjectUtils.isEmpty(admin)){
            return ResponseResult.fail();
        }
        try {
             adminService.updateUser(admin);
            return new ResponseResult(200, "修改用户成功", null);
        } catch (Exception e) {
            log.error("修改用户失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/saveOrUpdateAdmin")
    @PreAuthorize("hasAuthority('system:admin:saveOrUpdateAdmin')")
    public ResponseResult saveOrUpdateAdmin(@RequestBody Admin admin) {
        if (admin == null) {
            return ResponseResult.fail();
        }
        return adminService.saveOrUpdateAdmin(admin);
    }

    @PostMapping("/updateAdmin")
    @PreAuthorize("hasAuthority('system:admin:updateAdmin')")
    public ResponseResult updateAdmin(@RequestBody Admin admin) {
        try {
          adminService.updateAdmin(admin);
          return ResponseResult.success();
        } catch (Exception e) {
            log.error("更新管理员失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 通过id查询管理员方式
     *
     * @param id
     * @return
     */
    @GetMapping("/findAdminById")
    public ResponseResult findAdminById(@RequestParam Long id) {
        return adminService.findAdminById(id);
    }

    /**
     * 查询管理员
     *
     * @return
     */
    @GetMapping("/findAdmin")
    public ResponseResult findAdmin() {
        return adminService.findAdmin();
    }


    //通过id查询用户信息
    @GetMapping("/findUserById")
    public ResponseResult findUserById() {
        return adminService.findUsersById();
    }


    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @PreAuthorize("hasAuthority('system:admin:deleteById')")
    public ResponseResult deleteById(Long id) {
        try {
            Assert.notNull(id,"id不能为空");
            adminService.deleteById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error("删除用户失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 根据id更新用户状态
     * @param admin
     * @return
     */
    @PostMapping("/updateStates")
    @PreAuthorize("hasAuthority('system:admin:updateStates')")
    public ResponseResult updateStates(@RequestBody Admin admin) {
        try {
             adminService.updateStates(admin);
             return ResponseResult.success();
        } catch (Exception e) {
            log.error("修改状态失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
    }
    }


    /**
     * 查找用户所有状态数量
     *
     * @return
     */
    @GetMapping("/stateAll")
    @PreAuthorize("hasAuthority('system:admin:stateAll')")
    public ResponseResult stateAll() {
        try {
            List<Map<String, Object>> mapList = adminService.stateAll();
            return ResponseResult.success(mapList);
        } catch (Exception e) {
            log.error("获取所有状态失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 条件分页查询用户
     *
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody UserQuery query) {
        try {
            IPage<Admin> result = adminService.listPage(query);
            if (result==null){
                return ResponseResult.success(Collections.emptyList());
            }
            return ResponseResult.success(result.getRecords(),result.getTotal());
        } catch (Exception e) {
            log.error("获取列表失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 条件分页查询用户
     *
     * @param query
     * @return
     */
    @PostMapping("/listPageAdmin")
    public ResponseResult listPageAdmin(@RequestBody AdminQuery query) {
        try {
            IPage<Admin> result = adminService.listPageAdmin(query);
            if (result==null|| CollectionUtils.isEmpty(result.getRecords())){
                return ResponseResult.success(Collections.emptyList());
            }
            return ResponseResult.success(result.getRecords(),result.getTotal());
        } catch (Exception e) {
            log.error("获取列表失败={}", e.getMessage());
            return ResponseResult.fail(e.getMessage());
    }
}
}

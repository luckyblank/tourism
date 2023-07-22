package com.hzw.tourism.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.entity.Menu;
import com.hzw.tourism.vo.MenuListVo;

/**
 * @author xiaohuang
 * @date 2023/6/26
 */
public interface MenuService extends IService<Menu> {
    MenuListVo selectList(Long roleId) throws Exception;

}

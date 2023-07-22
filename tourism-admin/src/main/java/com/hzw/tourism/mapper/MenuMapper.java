package com.hzw.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzw.tourism.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
 /**
  * 查看用户拥有的权限
  * @param userid
  * @return
  */
 List<String> selectPermsByUserId(Long userid);
}

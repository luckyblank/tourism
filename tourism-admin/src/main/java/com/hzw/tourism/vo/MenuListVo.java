package com.hzw.tourism.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xiaohuang
 * @date 2023/7/9
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuListVo {
    private List<MenuListTreeVo> menuList;
    private List<Long> checkList;
    private List<Long> backList;
}

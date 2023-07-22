package com.hzw.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzw.tourism.entity.Admin;
import com.hzw.tourism.entity.Province;
import com.hzw.tourism.vo.ProvinceVo;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    public List<ProvinceVo> findProvinceByid();

    public List<Province> findfindProvinceAll();


}

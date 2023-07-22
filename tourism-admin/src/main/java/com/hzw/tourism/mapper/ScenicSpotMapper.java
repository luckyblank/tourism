package com.hzw.tourism.mapper;

import com.hzw.tourism.vo.SpotVo;
import com.hzw.tourism.entity.ScenicSpot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
public interface ScenicSpotMapper extends BaseMapper<ScenicSpot> {

    @Select("SELECT `scenic_spot`.`ID` spotId,`scenic_spot`.`SPOT_NAME` spotName FROM `scenic_spot`")
    List<SpotVo> getSpotHotel();
}

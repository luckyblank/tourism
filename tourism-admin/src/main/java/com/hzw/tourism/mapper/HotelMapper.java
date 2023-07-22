package com.hzw.tourism.mapper;

import com.hzw.tourism.vo.HotelVo;
import com.hzw.tourism.entity.Hotel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
public interface HotelMapper extends BaseMapper<Hotel> {

    @Select("SELECT `tb_hotel`.`ID` hotelId,`tb_hotel`.`HOTEL_NAME` hotelName FROM `tb_hotel` ")
    public List<HotelVo> getHotelSpot();

}

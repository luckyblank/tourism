package com.hzw.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.entity.ScenicSpot;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.vo.HotelVo;
import com.hzw.tourism.entity.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.qo.HotelQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
public interface HotelService extends IService<Hotel> {

    Integer count0() throws GlobalExceptionHandler;
    Integer count1() throws Exception;
    void saveOrUpdateHotel(Hotel hotel) throws Exception;
    Hotel findHotelById(Long id) throws Exception ;
    void deleteById(Long id) throws GlobalExceptionHandler;
    Boolean updateStates(Hotel hotel) throws Exception;
    List<AnalysisVO> typeAll() throws GlobalExceptionHandler;

    List<AnalysisVO> stateAll() throws GlobalExceptionHandler;
    Page<Hotel> listPage(HotelQuery query) throws GlobalExceptionHandler;

    List<Hotel> listHotelSpot(Long scenicSpotId) throws GlobalExceptionHandler;

    List<ScenicSpot> listSpotHotel(Long scenicSpotId) throws GlobalExceptionHandler;

    List<HotelVo> getHotelSpot();
//    ResponseResult listPage(QueryPageParam query);

}

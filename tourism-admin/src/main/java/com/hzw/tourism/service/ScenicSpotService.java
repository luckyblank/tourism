package com.hzw.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.exception.GlobalExceptionMyHandler;
import com.hzw.tourism.vo.AnalysisVO;
import com.hzw.tourism.vo.SpotVo;
import com.hzw.tourism.entity.ScenicSpot;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.qo.ScenciSpotQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
public interface ScenicSpotService extends IService<ScenicSpot> {

    Integer count0();

    Integer count1();

    Boolean saveOrUpdateScenicSpot(ScenicSpot scenicSpot) throws GlobalExceptionHandler;

    ScenicSpot findScenicSpotById(Long id) throws GlobalExceptionMyHandler;

    Boolean deleteById(Long id) throws GlobalExceptionHandler;

    Boolean updateStates(ScenicSpot scenicSpot) throws GlobalExceptionHandler;

    List<AnalysisVO> stateAll() throws GlobalExceptionHandler;

    List<ScenicSpot> findAll() throws GlobalExceptionHandler;

    IPage<ScenicSpot> listPage(ScenciSpotQuery query) throws GlobalExceptionHandler;

    List<SpotVo> getSpotHotel() throws GlobalExceptionHandler;
}

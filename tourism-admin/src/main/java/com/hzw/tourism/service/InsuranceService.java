package com.hzw.tourism.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzw.tourism.entity.Insurance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.qo.InsuranceQuery;
import com.hzw.tourism.vo.AnalysisVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
public interface InsuranceService extends IService<Insurance> {

    Integer count0() throws GlobalExceptionHandler;

    Integer count1() throws GlobalExceptionHandler;

    Boolean saveOrUpdateInsurance(Insurance insurance) throws GlobalExceptionHandler;

    Insurance findInsuranceById(Long id) throws GlobalExceptionHandler;

    Boolean deleteById(Long id) throws GlobalExceptionHandler;

    Boolean updateStates(Insurance insurance) throws GlobalExceptionHandler;

    List<AnalysisVO> typeAll() throws GlobalExceptionHandler;

    List<AnalysisVO> stateAll() throws GlobalExceptionHandler;

    List<Insurance> findAll() throws GlobalExceptionHandler;

    IPage<Insurance> listPage(InsuranceQuery query) throws GlobalExceptionHandler;


    List<AnalysisVO> InsuranceCompanyAll() throws GlobalExceptionHandler;
}

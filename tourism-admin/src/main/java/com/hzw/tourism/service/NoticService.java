package com.hzw.tourism.service;

import com.hzw.tourism.entity.Notic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-02-01
 */
public interface NoticService extends IService<Notic> {

    Notic getByNoticById(Long id) throws Exception;

    void saveOrUpdateNotic(Notic notic) throws Exception;

    void delete(Long id) throws Exception;

    void updateStates(Notic notic)throws Exception;

    List<Notic> getByNoticAll()throws Exception;

    List<Notic> getByNoticList()throws Exception;
}

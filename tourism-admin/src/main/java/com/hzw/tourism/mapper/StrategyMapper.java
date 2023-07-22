package com.hzw.tourism.mapper;

import com.hzw.tourism.entity.Strategy;
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
public interface StrategyMapper extends BaseMapper<Strategy> {

   @Select(" SELECT ID,ADD_USER_ID,ADD_TIME,DELETE_STATUS,MODIFY_TIME,IMG_URL,TITLE,RATING,SUMMARY,INTRO_URL,STATE,liked,comments,collects,product_type " +
           " FROM strategy " +
           " WHERE DELETE_STATUS=0  AND STATE=1 ORDER BY liked DESC LIMIT 0,5")
    List<Strategy> getListByLiked();

}

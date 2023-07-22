package com.hzw.tourism.mapper;

import com.hzw.tourism.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzw
 * @since 2023-01-19
 */
public interface CarMapper extends BaseMapper<Car> {
@Update("UPDATE tb_car SET state=#{state} WHERE DELETE_STATUS=0 AND ID =#{id} ")
    public boolean updateState(Car car);
}

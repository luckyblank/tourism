package com.hzw.tourism.mapper;

import com.hzw.tourism.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
public interface MessageMapper extends BaseMapper<Message> {
    /**
     * 改变状态
     * @param pId
     * @return
     */
    @Update("UPDATE `message` SET STATE=1 WHERE ID=#{pId}")
    public boolean updateStateByPid(Long pId);

}

package com.hzw.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hzw.tourism.entity.Notic;
import com.hzw.tourism.mapper.NoticMapper;
import com.hzw.tourism.service.NoticService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-02-01
 */
@Service
public class NoticServiceImpl extends ServiceImpl<NoticMapper, Notic> implements NoticService {

    @Override
    public Notic getByNoticById(Long id) throws Exception{
        Notic notic = getById(id);
        if (notic==null){
           throw new Exception("数据库没有公告id="+id);
        }
        return notic;
    }

    @Override
    public void saveOrUpdateNotic(Notic notic) throws Exception{
        Date date = new Date();
        if (notic.getId()==null){
            notic.setCreateTime(date);
            boolean save = save(notic);
            if (!save){
                throw new Exception("新增公告失败！！！");
            }
        }else {
            notic.setUpdateTime(date);
            boolean update = updateById(notic);
            if (!update){
                throw new Exception("新增公告失败！！！");
            }
        }
    }

    @Override
    public void delete(Long id)throws Exception{
        removeById(id);

    }

    @Override
    public void updateStates(Notic notic)throws Exception {
        Date date = new Date();
        notic.setUpdateTime(date);
        LambdaUpdateWrapper<Notic> noticLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        noticLambdaUpdateWrapper.and(i->i.eq(Notic::getId,notic.getId()).eq(Notic::getDeleteState,0))
                .set(Notic::getState,notic.getState());
        boolean update = update(noticLambdaUpdateWrapper);
        if (!update){
            throw new Exception("更新公告状态失败！！！");
        }
    }

    @Override
    public List<Notic> getByNoticAll() throws Exception{
        LambdaQueryWrapper<Notic> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Notic> wrapper = queryWrapper.eq( Notic::getState, 1);
        List<Notic> noticList = list(wrapper);
        return Optional.ofNullable(noticList).orElse(Collections.emptyList());

    }

    @Override
    public  List<Notic>  getByNoticList() throws Exception{
        List<Notic> noticList = list();
        return Optional.ofNullable(noticList).orElse(Collections.emptyList());
    }


}



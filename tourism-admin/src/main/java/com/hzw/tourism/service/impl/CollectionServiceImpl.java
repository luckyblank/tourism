package com.hzw.tourism.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.CustomCollection;
import com.hzw.tourism.mapper.CollectionMapper;
import com.hzw.tourism.qo.CollectQuery;
import com.hzw.tourism.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.hzw.tourism.constant.RedisConstants.CACHE_COLLECT_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-26
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CustomCollection> implements CollectionService {
@Autowired
private CollectionMapper collectionMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 添加收藏
     * @param collection
     * @return
     */
    @Override
    public ResponseResult saveCollection(CustomCollection collection) {
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long productId = collection.getProductId();
        Integer productType = collection.getProductType();
        //2.判断当前登录用户是否已经收藏
        String key = CACHE_COLLECT_KEY +userId+":"+productId;
        Boolean member = redisTemplate.opsForSet().isMember(key, userId.toString());
        if (BooleanUtil.isFalse(member)) {
            //3.如果未收藏，可以收藏
            //3.1数据库收藏数+1
           // boolean isSuccess = update().setSql("collects=collects+1").eq("id", collection.getId()).update();
            //3.2保存用户到Redis的set集合
            collection.setUserId(userId);
            collection.setAddTime(date);
            boolean save = save(collection);
            if (save) {
                redisTemplate.opsForSet().add(key, userId.toString());
                return new ResponseResult(200,"收藏成功",true);
            }
            return new ResponseResult(400,"收藏失败！！");
        } else {
            //4.如果已收藏 取消收藏
          //4.2把用户从Redis的set集合移除
                redisTemplate.opsForSet().remove(key, userId.toString());

            boolean remove =remove(new LambdaQueryWrapper<CustomCollection>()
                    .eq(CustomCollection::getProductId, productId)
                    .eq(CustomCollection::getProductType, productType));
            if (!remove){
                return new ResponseResult(400,"取消收藏失败");
                }
        }
        return new ResponseResult(200,"取消收藏成功",false);
    }


    @Override
    public ResponseResult findCollectionByPage(CollectQuery query) {
        Long userId = WebUtils.getUserId();
        //分页
        Page<CustomCollection> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());
        //条件查询
        LambdaQueryWrapper<CustomCollection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(userId!=null,CustomCollection::getUserId,userId);
            lambdaQueryWrapper.eq(query.getProductType()!=null,CustomCollection::getProductType,query.getProductType());

        Page<CustomCollection> result = collectionMapper.selectPage(page, lambdaQueryWrapper);
        if (result==null ||result.getRecords().isEmpty()){
            return ResponseResult.success(Collections.emptyList());
        }
        System.out.println(result);
        long total = result.getTotal();
        List<CustomCollection> records = result.getRecords();
        return ResponseResult.success(records,total);

    }

    @Override
    public ResponseResult isCollection(Long productId,Long productType) {
        Long userId = WebUtils.getUserId();
        // 查询数据库判断是否收藏
        Integer count = query().eq("Product_Id", productId).eq("Product_Type",productType).eq("USER_ID", userId).count();
        if (count==0){
            return new ResponseResult(400,"用户没有收藏",false);
        }
        return new ResponseResult(200,"用户已经收藏",true);

    }
}

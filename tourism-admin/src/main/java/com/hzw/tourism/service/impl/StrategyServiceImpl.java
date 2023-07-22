package com.hzw.tourism.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.constant.CommonConstants;
import com.hzw.tourism.entity.*;
import com.hzw.tourism.entity.CustomCollection;
import com.hzw.tourism.mapper.StrategyMapper;
import com.hzw.tourism.qo.StrategyQuery;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzw.tourism.service.*;
import com.hzw.tourism.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hzw.tourism.constant.RedisConstants.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@Slf4j
@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements StrategyService {

    @Autowired
    private CarService carService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoutelService routelService;
    @Autowired
    private ScenicSpotService scenicSpotService;

    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public ResponseResult likeStrategy(Long id) {
        //1.获取登录用户
        Long userId = WebUtils.getUserId();
        //2.判断当前登录用户是否已经点赞
        String key = CACHE_GIVEALIKE_KEY + id;
        Boolean member = redisTemplate.opsForSet().isMember(key, userId.toString());
        if (BooleanUtil.isFalse(member)) {
            //3.如果未点赞，可以点赞
            //3.1数据库点赞数+1
            //3.2保存用户到Redis的set集合
            boolean isSuccess = update().setSql("liked=liked+1").eq("ID", id).update();
            if (isSuccess) {
                redisTemplate.opsForSet().add(key, userId.toString());
            }
        } else {
            //4.如果已点赞 取消点赞
            //4.1数据库-1
            //4.2把用户从Redis的set集合移除
            boolean isSuccess = update().setSql("liked=liked-1").eq("ID", id).update();
            if (isSuccess) {
                redisTemplate.opsForSet().remove(key, userId.toString());
            }
        }
        return new ResponseResult(200,"点赞成功",true);
    }






    @Override
    public ResponseResult collectStrategy(Long id) {
        Long userId = WebUtils.getUserId();
        Strategy strategy = getById(id);
        Date date = new Date();
        //2.判断当前登录用户是否已经收藏
        String key = CACHE_COLLECT_KEY + id;
        Boolean member = redisTemplate.opsForSet().isMember(key, userId.toString());
        if (BooleanUtil.isFalse(member)) {
            //3.如果未收藏，可以收藏
            //3.1数据库收藏数+1
            //3.2保存用户到Redis的set集合
            boolean isSuccess = update().setSql("collects=collects+1").eq("ID", id).update();
            CustomCollection collection = new CustomCollection();
            collection.setProductType(5);
            collection.setProductId(id);
            collection.setTitle(strategy.getTitle());
            collection.setUserId(userId);
            collection.setAddTime(date);
            collectionService.save(collection);
            if (isSuccess) {
                redisTemplate.opsForSet().add(key, userId.toString());
            }
        } else {
            //4.如果已收藏 取消收藏
            //4.1数据库-1
            //4.2把用户从Redis的set集合移除
            boolean isSuccess = update().setSql("collects=collects-1").eq("ID", id).update();
            if (isSuccess) {
                redisTemplate.opsForSet().remove(key, userId.toString());
            }
            boolean remove = collectionService.remove(new LambdaQueryWrapper<CustomCollection>()
                    .eq(CustomCollection::getProductId, id)
                    .eq(CustomCollection::getProductType, 5)
            );
          if (!remove){
              return new ResponseResult(400,"取消收藏失败");
          }
        }
        return new ResponseResult(200,"收藏成功",true);
    }


    @Override
    public Strategy findStrategyById(Long id) {
        //1.查询攻略
        Strategy strategy = getById(id);
        //2.查询攻略有关用户
        strategyUser(strategy);
        //3.判断攻略是否被点赞
        isStrategy(strategy);
        //4.判断攻略是否被收藏
        isCollect(strategy);
        return strategy;

    }

    @Override
    public IPage<Strategy>  listPage(StrategyQuery query) {
        int corrent = query.getCurrentPage();
        int pageSize = query.getPageSize();
        Page<Strategy> page = query()
                .orderByDesc("liked")
                .page(new Page<>(corrent, pageSize));
        //条件查询
        LambdaQueryWrapper<Strategy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(query.getTitle()),Strategy::getTitle, query.getTitle());
        lambdaQueryWrapper.eq(query.getAddUserId()!=null,Strategy::getAddUserId, query.getAddUserId());
        lambdaQueryWrapper.eq(query.getProductType()!=null,Strategy::getProductType,query.getProductType());
        //分页
        IPage<Strategy> result = strategyMapper.selectPage(page, lambdaQueryWrapper);
        List<Strategy> records = result.getRecords();

        records.forEach(strategy -> {
            //查看博客用户
            this.strategyUser(strategy);
            //判断攻略是否被点赞
            this.isStrategy(strategy);
            //判断攻略是否被收藏
            this.isCollect(strategy);

        });
        return result;
    }

    @Override
    public  IPage<Strategy> listMyStrategy(StrategyQuery query) {
        Long userId = WebUtils.getUserId();
        int corrent = query.getCurrentPage();
        int pageSize = query.getPageSize();
        Page<Strategy> page = query()
                .orderByDesc("liked")
                .page(new Page<>(corrent, pageSize));
        //条件查询
        LambdaQueryWrapper<Strategy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(userId!=null,Strategy::getAddUserId, userId);
        //分页
        IPage<Strategy> result = strategyMapper.selectPage(page, lambdaQueryWrapper);
        List<Strategy> records = result.getRecords();
        records.forEach(strategy -> {
            //查看博客用户
            this.strategyUser(strategy);
            //判断攻略是否被点赞
            this.isStrategy(strategy);
            //判断攻略是否被收藏
            this.isCollect(strategy);

        });
        return result;
    }

    /**
     * 获取各个攻略类型列表
     * @param productType
     * @return
     */
    @Override
    public ResponseResult getProduct(Integer productType) {
        //订单类型车票
       if (productType==0) {
           List<Car> list = carService.list();
           return ResponseResult.success(Optional.ofNullable(list).orElse(Collections.emptyList()));
       }
       if (productType==1) {
           List<Hotel> list1 = hotelService.list();
           return ResponseResult.success(Optional.ofNullable(list1).orElse(Collections.emptyList()));
       }
       if (productType==2) {
           List<Routel> list2 = routelService.list();
           return ResponseResult.success(Optional.ofNullable(list2).orElse(Collections.emptyList()));
       }
       if (productType==3) {
           List<ScenicSpot> list3 = scenicSpotService.list();
           return ResponseResult.success(Optional.ofNullable(list3).orElse(Collections.emptyList()));
       }
        return ResponseResult.success(Collections.emptyList());

    }

    @Override
    public  List<Strategy> listRanking() {
        List<Strategy> listByLiked = strategyMapper.getListByLiked();

        return Optional.ofNullable(listByLiked).orElse(Collections.emptyList());
    }


    private void strategyUser(Strategy strategy) {
        Long userId = strategy.getAddUserId();
        Admin admin = adminService.getById(userId);
        strategy.setUserName(admin.getUsername());
        strategy.setIcon(admin.getIcon());
    }

    //判断攻略是否被点赞
    private void isStrategy(Strategy strategy) {
        //1.获取登录用户
        Long userId = WebUtils.getUserId();
        //2.判断当前登录用户是否已经点赞
        String key = CACHE_GIVEALIKE_KEY + strategy.getId();
        Boolean isMember = redisTemplate.opsForSet().isMember(key, userId.toString());
        strategy.setIsLike(BooleanUtil.isTrue(isMember));

    }


    //判断收藏是否被收藏
    private void isCollect(Strategy strategy) {
        //1.获取登录用户
        Long userId = WebUtils.getUserId();
        //2.判断当前登录用户是否已经点赞
        String key = CACHE_COLLECT_KEY + strategy.getId();
        Boolean isMember = redisTemplate.opsForSet().isMember(key, userId.toString());
        strategy.setIsCollect(BooleanUtil.isTrue(isMember));
    }

    @Override
    public Boolean saveOrUpdateStrategy(Strategy strategy) {
        //获取登录人信息
        Long userId = WebUtils.getUserId();
        Date date = new Date();
        Long id = strategy.getId();
        String key = CACHE_STRATEGY_KEY + id;
        //新增攻略
        //Integer productType = strategy.getProductType();
        if (id == null) {
            //封装用户信息
            Admin admin = adminService.getById(userId);
            strategy.setUserName(admin.getUsername());
            strategy.setAddUserId(userId);
            strategy.setIcon(admin.getIcon());
            strategy.setAddTime(date);
            boolean isSuccess = save(strategy);
            return isSuccess;
            //修改攻略
        } else {
            strategy.setModifyTime(date);
            boolean isSuccess = updateById(strategy);
            //保证时效性
            redisTemplate.delete(key);
            return isSuccess;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        String cacheMessageCount0Key = CACHE_STRATEGY_COUNT0_KEY;
        String cacheMessage_count1Key = CACHE_STRATEGY__COUNT1_KEY;
        Long userId = WebUtils.getUserId();
        String key=CACHE_STRATEGY__COUNT1_KEY+userId;
        Boolean isSuccess = deleteById(id);
        redisTemplate.delete(key);
        redisTemplate.delete(cacheMessageCount0Key);
        redisTemplate.delete(cacheMessage_count1Key);
        return isSuccess;
    }

    @Override
    public Boolean updateStates(Strategy strategy) {
        Date date = new Date();
        strategy.setModifyTime(date);
        strategy.setAddTime(date);
        LambdaUpdateWrapper<Strategy> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.and(i -> i.eq(Strategy::getId, strategy.getId()).eq(Strategy::getState, 1))
                .set(Strategy::getState, strategy.getState());
        boolean isSuccess = update(lambdaUpdateWrapper);
        return isSuccess;
    }



    @Override
    public List<Map<String, Object>> stateAll() {
        Integer count1 = lambdaQuery().eq(Strategy::getDeleteStatus, 0).eq(Strategy::getState, 1).count();
        Integer count2 = lambdaQuery().eq(Strategy::getDeleteStatus, 0).eq(Strategy::getState, 2).count();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", "发布");
        map1.put("value", count1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", "待发布");
        map2.put("value", count2);
        mapList.add(map1);
        mapList.add(map2);
        return mapList;
    }

    @Override
    public List<Strategy> findAllUser() {
        Long userId = WebUtils.getUserId();
        String key=CACHE_STRATEGY__COUNT1_KEY+userId;
        String strategyAll = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(strategyAll)) {
            List<Strategy> strategyList = JSON.parseArray(strategyAll, Strategy.class);
            log.info("走缓存....");
            return Optional.ofNullable(strategyList).orElse(Collections.emptyList());
        } else {
            List<Strategy> list = lambdaQuery()
                    .eq(Strategy::getAddUserId, userId)
                    .eq(Strategy::getState, 1).list();

            redisTemplate.opsForValue().set(key, JSON.toJSONString(list), CommonConstants.FOUR, TimeUnit.HOURS);
            return Optional.ofNullable(list).orElse(Collections.emptyList());
        }
    }




}

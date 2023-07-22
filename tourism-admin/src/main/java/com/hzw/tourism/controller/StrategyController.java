package com.hzw.tourism.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzw.tourism.DTO.StrategyDTO;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.CustomCollection;
import com.hzw.tourism.entity.Strategy;
import com.hzw.tourism.exception.GlobalExceptionHandler;
import com.hzw.tourism.qo.StrategyQuery;
import com.hzw.tourism.service.CollectionService;
import com.hzw.tourism.service.StrategyService;
import com.hzw.tourism.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private CollectionService collectionService;

    //点赞和取消点赞
    @GetMapping("/likeStrategy")
    public ResponseResult likeStrategy(Long id) throws GlobalExceptionHandler {
        Assert.notNull(id,"id不能为null");
        return strategyService.likeStrategy(id);
    }

    //点赞和取消点赞
    @GetMapping("/collectStrategy")
    public ResponseResult collectStrategy(Long id) throws GlobalExceptionHandler {
        Assert.notNull(id,"id不能为null");
        return strategyService.collectStrategy(id);
    }

    /**
     * 新增或修改攻略方式
     *
     * @param strategy
     * @return
     */
    @PostMapping("/saveOrUpdateStrategy")
    public ResponseResult saveOrUpdateStrategy(@RequestBody Strategy strategy) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(strategy)){
            return ResponseResult.fail("strategy不能为null");
        }
        Boolean isSuccess = strategyService.saveOrUpdateStrategy(strategy);
        return ResponseResult.isSuccess(isSuccess, ObjectUtils.isEmpty(strategy.getId())?"新注":"修改");

    }

    /**
     * 通过id查询攻略方式
     *
     * @param id
     * @return
     */
    @GetMapping("/findStrategyById")
    public ResponseResult findStrategyById(@RequestParam Long id) throws GlobalExceptionHandler {
        Assert.notNull(id,"id不能为null");
        Strategy strategy = strategyService.findStrategyById(id);
        return ResponseResult.success(strategy);

    }

    /**
     * 工具id删除攻略信息
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public ResponseResult deleteById(Long id) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(id)){
            return ResponseResult.fail("id不能为null");
        }
        return ResponseResult.isSuccess(strategyService.deleteById(id),"删除");

    }

    /**
     * 根据id删除攻略信息
     *
     * @param strategy
     * @return
     */
    @PostMapping("/updateStates")
    public ResponseResult updateStates(@RequestBody Strategy strategy) throws GlobalExceptionHandler {
        if (ObjectUtils.isEmpty(strategy)){
            return ResponseResult.fail("strategy不能为null");
        }
        return ResponseResult.isSuccess(strategyService.updateStates(strategy),"修改");
    }


    /**
     * 查找所有状态数量
     *
     * @return
     */
    @GetMapping("/stateAll")
    //@PreAuthorize("hasAuthority('system:strategy:stateAll')")
    public ResponseResult stateAll() throws GlobalExceptionHandler {
        return ResponseResult.success( strategyService.stateAll());

    }

    /**
     * 查找用户所有发表攻略
     *
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll() throws GlobalExceptionHandler{
        return ResponseResult.success(strategyService.findAllUser());

    }

    /**
     * 列表条件分页查询攻略
     *
     * @param query
     * @return
     */
    @PostMapping("/listPage")
    public ResponseResult listPage(@RequestBody StrategyQuery query) throws GlobalExceptionHandler {
        IPage<Strategy> result = strategyService.listPage(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());

    }


    @PostMapping("/listRanking")
    public ResponseResult listRanking() throws GlobalExceptionHandler {
        return ResponseResult.success(strategyService.listRanking());
    }

    /**
     * 用户发表攻略列表
     *
     * @param query
     * @return
     */
    @PostMapping("/listMyStrategy")
    public ResponseResult listMyStrategy(StrategyQuery query) throws GlobalExceptionHandler {
        IPage<Strategy> result = strategyService.listMyStrategy(query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(result.getRecords(),result.getTotal());

    }

    @PostMapping("/deleteByUser")
    public ResponseResult deleteByUser(@RequestBody StrategyDTO strategyDTO) {
        Long userId = WebUtils.getUserId();
        LambdaQueryWrapper<Strategy> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Strategy::getAddUserId, userId);
        queryWrapper.eq(Strategy::getId, strategyDTO.getProductId());
        boolean remove = strategyService.remove(queryWrapper);
        if (!remove) {
            return ResponseResult.fail();
        }

        LambdaQueryWrapper<CustomCollection> collectionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        collectionLambdaQueryWrapper.eq(CustomCollection::getProductId, strategyDTO.getProductId());
        CustomCollection collection = collectionService.getOne(collectionLambdaQueryWrapper);
        if (collection != null) {
            boolean remove2 = collectionService.remove(new LambdaQueryWrapper<CustomCollection>()
                    .eq(CustomCollection::getProductId, strategyDTO.getProductId())
                    .eq(CustomCollection::getProductType, strategyDTO.getProductType())
            );
            if (!remove2) {
                return ResponseResult.fail();
            }
        }

        return ResponseResult.success();
    }

    @GetMapping("/getProduct")
    public ResponseResult getProduct(Integer productType) throws GlobalExceptionHandler {

        return strategyService.getProduct(productType);

    }
}

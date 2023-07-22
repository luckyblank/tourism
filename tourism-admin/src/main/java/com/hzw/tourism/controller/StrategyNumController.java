package com.hzw.tourism.controller;
import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.StrategyNum;
import com.hzw.tourism.service.StrategyNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-31
 */
@RestController
@RequestMapping("strategyNum")
public class StrategyNumController {
    @Autowired
    private StrategyNumService strategyNumService;

    @GetMapping("/getStrategyNumById")
    public ResponseResult getStrategyNumById(Long id){
        StrategyNum strategyNum = strategyNumService.getById(id);
        return ResponseResult.success(strategyNum);
    }


}

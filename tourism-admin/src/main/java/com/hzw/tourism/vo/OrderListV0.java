package com.hzw.tourism.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaohuang
 * @date 2023/7/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListV0 {
    private OrderSumVo orderSumVo;
    private Long total;
}

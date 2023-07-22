package com.hzw.tourism.qo;

import lombok.Data;


@Data
public class OrderQuery extends Query {
    private String userName;
    private Integer userId;
    private Integer productType;


}

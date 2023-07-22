package com.hzw.tourism.qo;

import lombok.Data;

@Data
public class MessageQuery extends Query {
    private String userName;
    private Long userId;
    private Integer state;
    private Long id;
    private Long pid;
}

package com.hzw.tourism.comon;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static int PAGE_SIZE=5;
    private static int PAGE_NUM=1;
    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    private String startPlace;
    private String endPlace;
    private HashMap param=new HashMap();
}

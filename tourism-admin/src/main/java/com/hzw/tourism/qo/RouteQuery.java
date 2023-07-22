package com.hzw.tourism.qo;
import lombok.Data;
@Data
public class RouteQuery extends Query {
    private  String startSite;
    private String endSite;
    private Double price;
    private Integer state;

}

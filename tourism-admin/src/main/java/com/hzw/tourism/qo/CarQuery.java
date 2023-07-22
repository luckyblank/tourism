package com.hzw.tourism.qo;


import lombok.Data;


@Data
public class CarQuery extends Query {
    private String startPlace;
    private String endPlace;
    private String intermediateStop;
    private Integer type;
    private Integer state;



}

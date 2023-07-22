package com.hzw.tourism.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelQuery extends Query{
    private String hotelName;
    private Integer hotelStar;
    private Integer type;
    private Double price;
    private Long scenicSpotId;
    private Integer state;
    private String address;

}

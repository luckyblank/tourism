package com.hzw.tourism.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long productId;
    private Integer orderType;
    private Integer peopleCount;
    private String requirement;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("SETOFF_TIME")
    private Date setoffTime;
    private String name;
    private String linkTel;
    private Integer day;
    private Double price;

}

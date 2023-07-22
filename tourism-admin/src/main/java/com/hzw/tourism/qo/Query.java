package com.hzw.tourism.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query implements Serializable {
    private Integer currentPage = 1;
    private Integer pageSize = 5;
}

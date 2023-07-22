package com.hzw.tourism.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzw
 * @since 2023-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Province {
    private Long province;
    private String addr;
}

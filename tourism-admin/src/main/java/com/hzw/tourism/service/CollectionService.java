package com.hzw.tourism.service;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.CustomCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzw.tourism.qo.CollectQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzw
 * @since 2023-01-26
 */
public interface CollectionService extends IService<CustomCollection> {

    ResponseResult saveCollection(CustomCollection collection);

    ResponseResult findCollectionByPage(CollectQuery query);

    ResponseResult isCollection(Long productId,Long productType);
}

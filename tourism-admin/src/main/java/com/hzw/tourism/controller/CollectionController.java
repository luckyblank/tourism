package com.hzw.tourism.controller;


import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.entity.CustomCollection;
import com.hzw.tourism.qo.CollectQuery;
import com.hzw.tourism.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzw
 * @since 2023-01-26
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 显示用户所有收藏
     * @param query
     * @return
     */
    @PostMapping("/findCollectionByPage")
    public ResponseResult findCollectionByPage(@RequestBody CollectQuery query){
        return collectionService.findCollectionByPage(query);
    }

    /**
     * 新增收藏
     * @param collection
     * @return
     */
    @PostMapping("/save")
    public ResponseResult saveCollection(@RequestBody CustomCollection collection){

        return collectionService.saveCollection(collection);
    }

    /**
     * 判断用户是否收藏
     * @param productId
     * @param productType
     * @return
     */
    //收藏按钮回显
    @GetMapping("/isCollection")
    public ResponseResult isCollection(Long productId,Long productType){
        //2.已登录,判断是否收藏
        return collectionService.isCollection(productId,productType);
    }
}

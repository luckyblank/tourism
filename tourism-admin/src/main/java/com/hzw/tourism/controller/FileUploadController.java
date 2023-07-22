package com.hzw.tourism.controller;

import com.hzw.tourism.comon.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam(value = "file",required = false) MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        String path = "D:\\vue_project\\travel\\src\\assets\\images\\";
        File dest=new File(path+fileName);
        // 判断文件是否存在
        if(!dest.getParentFile().exists()){
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            //前台上传
            return new ResponseResult(200, "文件上传成功", fileName);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail();
        }


    }

    @PostMapping("/uploadUser")
    public ResponseResult uploadFileUser(@RequestParam(value = "file",required = false) MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        String path2 = "D:\\vue_project\\travel3\\front-end-of-tourism-network-master\\src\\assets\\images\\";
        File dest2=new File(path2+fileName);
        // 判断文件是否存在
        if(!dest2.getParentFile().exists()){
            // 不存在就创建一个
            dest2.getParentFile().mkdirs();
        }
        try {
            // 上传
            file.transferTo(dest2);
            return new ResponseResult(200, "上传图片成功", fileName);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail();
        }


    }
}





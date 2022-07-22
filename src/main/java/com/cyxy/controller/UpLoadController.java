package com.cyxy.controller;


import com.cyxy.domian.ResponseResult;
import com.cyxy.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UpLoadController {
    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        System.out.println(img);
        return uploadService.uploadImg(img);
    }
}
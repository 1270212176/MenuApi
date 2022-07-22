package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.service.BannersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/banners")
public class BannersController {

    @Resource
    private BannersService bannersService;

    @GetMapping("/gets")
    public ResponseResult findBanners(){
        return bannersService.findBanners();
    }
}

package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Banners;

public interface BannersService extends IService<Banners> {
    ResponseResult findBanners();
}

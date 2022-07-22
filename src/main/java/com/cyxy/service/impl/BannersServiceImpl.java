package com.cyxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Banners;
import com.cyxy.mapper.BannersMapper;
import com.cyxy.service.BannersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannersServiceImpl extends ServiceImpl<BannersMapper, Banners> implements BannersService {

    @Resource
    private BannersMapper bannersMapper;

    @Override
    public ResponseResult findBanners() {
        List<Banners> banners = bannersMapper.selectList(null);
        return ResponseResult.okResult(banners);
    }
}

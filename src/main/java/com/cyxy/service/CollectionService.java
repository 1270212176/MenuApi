package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Collection;

public interface CollectionService extends IService<Collection> {
    ResponseResult addCol(Integer fid, String name, String images);

    ResponseResult findAll();

    ResponseResult delCol(Integer fid);

    ResponseResult getByOpenid(String openid);

    ResponseResult colExist(String openid, String fid);

    ResponseResult add(Integer fid, String openid);

    ResponseResult del(Integer fid, String openid);
}

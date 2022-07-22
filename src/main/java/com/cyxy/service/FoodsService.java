package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.vo.FoodsVo;

public interface FoodsService extends IService<Foods> {
    ResponseResult addFoods(Foods foods);

    ResponseResult FindResFoods(Integer pageSize);

    ResponseResult findSearchFoods(int pageSize, String keywords, int page);

    ResponseResult findSeaFoods(Integer pageSize, String keywords, Integer pageNum);

    ResponseResult findById(int id);


    ResponseResult adminGetFoods(Integer pageNum, Integer pageSize);

    ResponseResult addFood(FoodsVo foodsVo);

    ResponseResult adminGetById(Integer id);

    ResponseResult adminUpdateFood(FoodsVo foodsVo);

    ResponseResult adminDeleteById(Integer id);
}

package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Category;

public interface CategoryService extends IService<Category> {
    ResponseResult getAllCategory();

    ResponseResult findCategoryById(int id);

    ResponseResult adminGetAll();

    ResponseResult adminGetAllCateGory(Integer pageNum, Integer pageSize);

    ResponseResult adminAddCg(Category category);

    ResponseResult adminGetById(Integer id);

    ResponseResult adminUpdateCg(Category category);

    ResponseResult deleteById(Integer id);
}

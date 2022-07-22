package com.cyxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Category;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.vo.CategoryVo;
import com.cyxy.mapper.CategoryMapper;
import com.cyxy.mapper.FoodsMapper;
import com.cyxy.service.CategoryService;
import com.cyxy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private FoodsMapper foodsMapper;

    @Override
    public ResponseResult getAllCategory() {
        List<Category> categories = categoryMapper.selectList(null);

        return ResponseResult.okResult(categories);
    }

    @Override
    public ResponseResult findCategoryById(int id) {
        LambdaQueryWrapper<Foods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Foods::getTid,id);
        List<Foods> foods = foodsMapper.selectList(queryWrapper);
        return ResponseResult.okResult(foods);
    }

    @Override
    public ResponseResult adminGetAll() {
        List<Category> categories = categoryMapper.selectList(null);
        List<String> collect = categories.stream()
                .map(category -> category.getName())
                .collect(Collectors.toList());
        return ResponseResult.okResult(collect);
    }

    @Override
    public ResponseResult adminGetAllCateGory(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        Page<Category> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Category> records = page.getRecords();
        int total = (int)page.getTotal();
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(records, CategoryVo.class);
        for (CategoryVo categoryVo:categoryVos){
            categoryVo.setTotal(total);
        }
        return ResponseResult.okResult(categoryVos);

    }

    @Override
    public ResponseResult adminAddCg(Category category) {
        String name = category.getName();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName,name);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        if (categories.size()>0){
            return ResponseResult.okResult("fail");
        }

        categoryMapper.insert(category);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminGetById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return ResponseResult.okResult(category);
    }

    @Override
    public ResponseResult adminUpdateCg(Category category) {
        categoryMapper.updateById(category);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        categoryMapper.deleteById(id);
        return ResponseResult.okResult("success");
    }
}

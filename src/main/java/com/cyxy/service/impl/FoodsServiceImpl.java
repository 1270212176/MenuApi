package com.cyxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Category;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.vo.FoodsVo;
import com.cyxy.domian.vo.SearechVo;
import com.cyxy.mapper.CategoryMapper;
import com.cyxy.mapper.FoodsMapper;
import com.cyxy.service.FoodsService;
import com.cyxy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class FoodsServiceImpl extends ServiceImpl<FoodsMapper, Foods> implements FoodsService {

    @Resource
    private FoodsMapper foodsMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult addFoods(Foods foods) {
        int insert = foodsMapper.insert(foods);
        if (insert>0){
            return ResponseResult.okResult("添加成功");
        }
        return ResponseResult.okResult("添加失败");
    }

    @Override
    public ResponseResult FindResFoods(Integer pageSize) {
        LambdaQueryWrapper<Foods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Foods::getFlag,1);
        Page<Foods> page = new Page<>(1,pageSize);
        page(page,queryWrapper);
        List<Foods> records = page.getRecords();

        return ResponseResult.okResult(records);
    }

    @Override
    public ResponseResult findSearchFoods(int pageSize, String keywords, int page) {

        System.out.println(keywords);
        if(Objects.isNull(keywords)){
            LambdaQueryWrapper<Foods> queryWrapper = new LambdaQueryWrapper<>();

            Page<Foods> page1 = new Page<>(page,pageSize);
            page(page1,queryWrapper);
            List<Foods> records = page1.getRecords();
            SearechVo vo = new SearechVo(records,page1.getTotal(),pageSize,page);
            return ResponseResult.okResult(vo);
        }
        LambdaQueryWrapper<Foods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Foods::getName,keywords);
        Page<Foods> page1 = new Page<>(page,pageSize);
        page(page1,queryWrapper);
        List<Foods> records = page1.getRecords();
        SearechVo vo = new SearechVo(records,page1.getTotal(),pageSize,page);
        return ResponseResult.okResult(vo);




    }

    @Override
    public ResponseResult findSeaFoods(Integer pageSize, String keywords, Integer pageNum) {
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();


        return null;
    }

    @Override
    public ResponseResult findById(int id) {
        Foods foods = foodsMapper.selectById(id);

        return ResponseResult.okResult(foods);
    }

    @Override
    public ResponseResult adminGetFoods(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Foods> queryWrapper = new LambdaQueryWrapper<>();
        Page<Foods> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Foods> records = page.getRecords();
        int total = (int)page.getTotal();
        List<FoodsVo> foodsVos = BeanCopyUtils.copyBeanList(records, FoodsVo.class);
        for (FoodsVo foodsVo:foodsVos){
            foodsVo.setTotal(total);
            Category category = categoryMapper.selectById(foodsVo.getTid());
            foodsVo.setTname(category.getName());
            Integer flag = foodsVo.getFlag();
            if (flag==1){
                foodsVo.setType("是");
            }
            else {
                foodsVo.setType("否");
            }
        }
        return ResponseResult.okResult(foodsVos);
    }

    @Override
    public ResponseResult addFood(FoodsVo foodsVo) {
        Foods foods = new Foods();
        foods.setName(foodsVo.getName());
        foods.setImages(foodsVo.getImages());
        foods.setContent(foodsVo.getContent());
        foods.setMaterial(foodsVo.getMaterial());
        String type = foodsVo.getType();
        String tname = foodsVo.getTname();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName,tname);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        Integer id = categories.get(0).getId();
        foods.setTid(id);
        if (type.equals("是"))
        {
            foods.setFlag(1);
        }
        else {
            foods.setFlag(0);
        }
        foodsMapper.insert(foods);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminGetById(Integer id) {
        Foods foods = foodsMapper.selectById(id);

        return ResponseResult.okResult(foods);
    }

    @Override
    public ResponseResult adminUpdateFood(FoodsVo foodsVo) {
        Foods foods = new Foods();
        foods.setId(foodsVo.getId());
        foods.setName(foodsVo.getName());
        foods.setTid(foodsVo.getTid());
        foods.setImages(foodsVo.getImages());
        foods.setContent(foodsVo.getContent());
        foods.setMaterial(foodsVo.getMaterial());
        foods.setFlag(foodsVo.getFlag());
        foodsMapper.updateById(foods);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminDeleteById(Integer id) {
        foodsMapper.deleteById(id);
        return ResponseResult.okResult("success");
    }


}

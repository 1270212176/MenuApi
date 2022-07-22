package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.vo.FoodsVo;
import com.cyxy.service.FoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/foods")
public class FoodsController {
    @Resource
    private FoodsService foodsService;

    @GetMapping("/res/{pageSize}")
    public ResponseResult FindResFoods(@PathVariable("pageSize") Integer pageSize){
        return foodsService.FindResFoods(pageSize);
    }

    @GetMapping("/search")
    public ResponseResult findSearchFoods(int pageSize,String keywords,int page){


        ResponseResult responseResult = foodsService.findSearchFoods(pageSize,keywords,page);
        return responseResult;
    }

    @GetMapping("/{id}")
    public ResponseResult findById(@PathVariable int id){
        return foodsService.findById(id);
    }

    @PostMapping("/addfoods")
    public ResponseResult addFoods(@RequestBody Foods foods){
        return foodsService.addFoods(foods);
    }


    @PostMapping("/addFood")
    public ResponseResult addFood(@RequestBody FoodsVo foodsVo){
        System.out.println(foodsVo);
        return foodsService.addFood(foodsVo);
    }

    @GetMapping("/admin/getFoods/{pageNum}/{pageSize}")
    public ResponseResult adminGetFoods(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){
        return foodsService.adminGetFoods(pageNum,pageSize);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult adminGetById(@PathVariable("id") Integer id){
        return foodsService.adminGetById(id);
    }

    @PutMapping("/admin/updateFood")
    public ResponseResult adminUpdateFood(@RequestBody FoodsVo foodsVo){
        System.out.println(foodsVo);
        return foodsService.adminUpdateFood(foodsVo);
    }

    @DeleteMapping("/admin/deleteById/{id}")
    public ResponseResult adminDeleteById(@PathVariable("id") Integer id){
        return foodsService.adminDeleteById(id);
    }

}

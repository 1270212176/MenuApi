package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Category;
import com.cyxy.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cg")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseResult getAllCategory()
    {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseResult findCategoryById(@PathVariable int id){
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/admin/all")
    public ResponseResult adminGetAll(){
        return categoryService.adminGetAll();
    }

    @GetMapping("/admin/getAll/{pageNum}/{pageSize}")
    public ResponseResult adminGetAllCateGory(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize") Integer pageSize)
    {
        return categoryService.adminGetAllCateGory(pageNum,pageSize);
    }

    @PostMapping("/admin/addCg")
    public ResponseResult adminAddCg(@RequestBody Category category){
        System.out.println("请求拿到了");
        System.out.println(category);
        return categoryService.adminAddCg(category);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult adminGetById(@PathVariable("id") Integer id)
    {
        return categoryService.adminGetById(id);
    }

    @PutMapping("/admin/updateCg")
    public ResponseResult adminUpdateCg(@RequestBody Category category){
        System.out.println(category);
        return categoryService.adminUpdateCg(category);
    }

    @DeleteMapping("/admin/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id){
        return categoryService.deleteById(id);
    }
}

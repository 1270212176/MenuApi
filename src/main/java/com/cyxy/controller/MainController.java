package com.cyxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Category;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.entity.Link;
import com.cyxy.domian.entity.Users;
import com.cyxy.mapper.CategoryMapper;
import com.cyxy.mapper.FoodsMapper;
import com.cyxy.mapper.LinkMapper;
import com.cyxy.mapper.UsersMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    @Resource
    private FoodsMapper foodsMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private LinkMapper linkMapper;

    @Resource
    private UsersMapper usersMapper;

    @GetMapping("/admin/getTotalInfo")
    public ResponseResult getTotalInfo(){
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getType,0);
        List<Users> users = usersMapper.selectList(queryWrapper);
        int userNum = users.size();
        List<Foods> foods = foodsMapper.selectList(null);
        int foodNum = foods.size();
        List<Category> categories = categoryMapper.selectList(null);
        int cgNum = categories.size();
        LambdaQueryWrapper<Link> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Link::getStatus,0);
        List<Link> links = linkMapper.selectList(queryWrapper1);
        int linkNum = links.size();
        int [] total = {foodNum,cgNum,userNum,linkNum};
        return ResponseResult.okResult(total);
    }

}

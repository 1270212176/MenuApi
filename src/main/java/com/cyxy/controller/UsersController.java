package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Users;
import com.cyxy.service.UsersService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Users users){
        System.out.println(users);
        System.out.println("拿到了请求");
        return usersService.login(users);
    }

    @GetMapping("/getUserInfo/{openid}")
    public ResponseResult getUserInfo(@PathVariable("openid") String openid){
        return usersService.getUserInfo(openid);
    }

    @PutMapping("/edit")
    public ResponseResult editUserInfo(@RequestBody Users users){
        System.out.println("进入修改");
        System.out.println(users);
        return usersService.editUserInfo(users);
    }

    @GetMapping("/admin/login/{username}/{password}")
    public ResponseResult adminLogin(@PathVariable("username") String username,@PathVariable("password") String password){
        return usersService.adminLogin(username,password);
    }

    @GetMapping("/admin/getList/{pageNum}/{pageSize}")
    public ResponseResult adminGetList(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize)
    {
        return usersService.adminGetList(pageNum,pageSize);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult adminGetById(@PathVariable("id") long id)
    {
        return usersService.adminGetById(id);
    }

    @PutMapping("/admin/updateUser")
    public ResponseResult adminUpdate(@RequestBody Users users){
        System.out.println(users);
        return usersService.adminUpdate(users);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseResult adminDelById(@PathVariable("id") long id)
    {
        System.out.println(id);
        return usersService.adminDelById(id);
    }
}

package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Link;
import com.cyxy.service.LinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/all")
    public ResponseResult getAllLinks()
    {
        return linkService.getAllLinks();
    }

    @GetMapping("/admin/getAllLink/{pageNum}/{pageSize}")
    public ResponseResult getAllLink(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize") Integer pageSize)
    {
        return linkService.getAllLink(pageNum,pageSize);
    }

    @PostMapping("/admin/addLink")
    public ResponseResult adminAddLink(@RequestBody Link link){
        System.out.println(link);
        return linkService.adminAddLink(link);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult adminGetById(@PathVariable("id") Integer id){
        return linkService.adminGetById(id);
    }

    @PutMapping("/admin/updateLink")
    public ResponseResult adminUpdateLink(@RequestBody Link link){
        System.out.println(link);
        return linkService.adminUpdateLink(link);
    }

    @DeleteMapping("/admin/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id){
        return linkService.deleteById(id);
    }
}

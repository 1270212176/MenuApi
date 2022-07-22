package com.cyxy.controller;

import com.cyxy.domian.ResponseResult;
import com.cyxy.service.CollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/col")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    @PostMapping("/addcol")
    public ResponseResult addCol(Integer fid, String name, String images){
        return collectionService.addCol(fid,name,images);
    }

    @PostMapping("/add/{fid}/{openid}")
    public ResponseResult add(@PathVariable("fid") Integer fid,@PathVariable("openid") String openid){
        return collectionService.add(fid,openid);
    }

    @GetMapping("/all")
    public ResponseResult findAll(){
        return collectionService.findAll();
    }

    @DeleteMapping("/del/{fid}")
    public ResponseResult delCol(@PathVariable("fid") Integer fid){
        return collectionService.delCol(fid);
    }

    @DeleteMapping("/del/{fid}/{openid}")
    public ResponseResult del(@PathVariable("fid") Integer fid,@PathVariable("openid") String openid){
        return collectionService.del(fid,openid);

    }

    @GetMapping("getByOpenid/{openid}")
    public ResponseResult getByOpenid(@PathVariable("openid") String openid){
        return collectionService.getByOpenid(openid);
    }

    @GetMapping("/colExist/{openid}/{fid}")
    public ResponseResult colExist(@PathVariable("openid")String openid,@PathVariable("fid") String fid){
        return collectionService.colExist(openid,fid);
    }
}

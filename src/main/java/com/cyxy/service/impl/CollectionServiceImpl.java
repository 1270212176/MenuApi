package com.cyxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Collection;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.vo.CollectionVo;
import com.cyxy.mapper.CollectionMapper;
import com.cyxy.mapper.FoodsMapper;
import com.cyxy.service.CollectionService;
import com.cyxy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private FoodsMapper foodsMapper;


    @Override
    public ResponseResult addCol(Integer fid, String name, String images) {
        return null;
    }

    @Override
    public ResponseResult findAll() {
        List<Collection> collections = collectionMapper.selectList(null);

        return ResponseResult.okResult(collections);
    }

    @Override
    public ResponseResult delCol(Integer fid) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getFid,fid);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        Collection collection = collections.get(0);
        int id = collection.getId();
        System.out.println(id);
        int i = collectionMapper.deleteById(id);
        if (i>0){
            return ResponseResult.okResult("删除成功");
        }
        return ResponseResult.okResult("删除失败");
    }

    @Override
    public ResponseResult getByOpenid(String openid) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getOpenid,openid);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        List<CollectionVo> collectionVos = BeanCopyUtils.copyBeanList(collections, CollectionVo.class);
        for (CollectionVo collectionVo:collectionVos){
            Foods foods = foodsMapper.selectById(collectionVo.getFid());
            collectionVo.setName(foods.getName());
            collectionVo.setImages(foods.getImages());
        }
        return ResponseResult.okResult(collectionVos);
    }

    @Override
    public ResponseResult colExist(String openid, String fid) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getFid,fid);
        queryWrapper.eq(Collection::getOpenid,openid);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        if (collections.size()>0){
            return ResponseResult.okResult("success");
        }
        return ResponseResult.okResult("fail");
    }

    @Override
    public ResponseResult add(Integer fid, String openid) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getFid,fid);
        queryWrapper.eq(Collection::getOpenid,openid);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        if (collections.size()>0){
            return ResponseResult.okResult("fail");
        }
        Collection collection = new Collection();
        collection.setFid(fid);
        collection.setOpenid(openid);
        collectionMapper.insert(collection);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult del(Integer fid, String openid) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getFid,fid);
        queryWrapper.eq(Collection::getOpenid,openid);
        int delete = collectionMapper.delete(queryWrapper);
        if (delete>0){
            return ResponseResult.okResult("success");
        }
        return ResponseResult.okResult("fail");
    }
}

package com.cyxy;

import com.cyxy.domian.entity.Collection;
import com.cyxy.domian.entity.Foods;
import com.cyxy.domian.entity.Users;
import com.cyxy.mapper.CollectionMapper;
import com.cyxy.mapper.FoodsMapper;
import com.cyxy.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class mpTest {

    @Resource
    private FoodsMapper foodsMapper;

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private UsersMapper usersMapper;

    @Test
    public void test(){
        List<Foods> foods = foodsMapper.selectList(null);
        System.out.println(foods);
    }

    @Test
    public void test1(){
        List<Collection> collections = collectionMapper.selectList(null);
        System.out.println(collections);
    }

    @Test void test2(){
        Collection collection = collectionMapper.selectById(10);
        System.out.println(collection);
    }
    @Test
    public void test3(){
        List<Users> users = usersMapper.selectList(null);
        System.out.println(users);
    }
}

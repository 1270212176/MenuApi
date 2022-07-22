package com.cyxy.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

//Bean拷贝工具类封装
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    //单个对象的拷贝
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //利用反射来创建接收对象

        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))//对象的转换，将每个对象都进行拷贝
                .collect(Collectors.toList());
    }
}
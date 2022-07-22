package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Link;


/**
 * (Link)表服务接口
 *
 * @author makejava
 * @since 2022-07-08 15:38:13
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLinks();

    ResponseResult getAllLink(Integer pageNum, Integer pageSize);

    ResponseResult adminAddLink(Link link);

    ResponseResult adminGetById(Integer id);

    ResponseResult adminUpdateLink(Link link);

    ResponseResult deleteById(Integer id);
}

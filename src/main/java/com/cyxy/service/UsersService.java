package com.cyxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Users;


/**
 * (Users)表服务接口
 *
 * @author makejava
 * @since 2022-07-07 13:46:59
 */
public interface UsersService extends IService<Users> {

    ResponseResult login(Users users);

    ResponseResult getUserInfo(String openid);

    ResponseResult editUserInfo(Users users);

    ResponseResult adminLogin(String username, String password);

    ResponseResult adminGetList(Integer pageNum, Integer pageSize);

    ResponseResult adminGetById(long id);

    ResponseResult adminUpdate(Users users);

    ResponseResult adminDelById(long id);
}

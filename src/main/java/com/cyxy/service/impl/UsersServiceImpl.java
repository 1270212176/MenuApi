package com.cyxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.WxProperties;
import com.cyxy.domian.entity.Users;
import com.cyxy.domian.vo.UsersVo;
import com.cyxy.mapper.UsersMapper;
import com.cyxy.service.UsersService;
import com.cyxy.utils.BeanCopyUtils;
import com.cyxy.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 13:46:59
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private WxProperties wxProperties;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private HttpClientUtil httpClientUtil;

    @Override
    public ResponseResult login(Users users) {
        String jscode2sessionUrl = wxProperties.getJscode2sessionUrl()+"?appid="+wxProperties.getAppid()+"&secret="+wxProperties.getAppsecret()+"&js_code="+users.getCode()+"&grant_type=authorization_code";
        System.out.println(jscode2sessionUrl);
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid =jsonObject.get("openid").toString();
        System.out.println("openid="+openid);
        LambdaQueryWrapper<Users> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getOpenid,openid);
        List<Users> wxUserInfos = usersMapper.selectList(queryWrapper);
        if (wxUserInfos.size()==0){
            System.out.println("不存在，插入用户");
            users.setOpenid(openid);
            users.setRegisterdate(new Date());
            users.setLastlogindate(new Date());
            usersMapper.insert(users);
        }
        else {
            System.out.println("存在，更新用户");
            Users users1 = wxUserInfos.get(0);
            users1.setNickName(users.getNickName());
            users1.setAvatarUrl(users.getAvatarUrl());
            users1.setLastlogindate(new Date());
            LambdaQueryWrapper<Users> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Users::getOpenid,openid);
            usersMapper.update(users1,queryWrapper1);

        }

        return ResponseResult.okResult(openid);
    }

    @Override
    public ResponseResult getUserInfo(String openid) {
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getOpenid,openid);
        List<Users> users = usersMapper.selectList(queryWrapper);
        return ResponseResult.okResult(users.get(0));
    }

    @Override
    public ResponseResult editUserInfo(Users users) {
        System.out.println("hhh"+users.getPhone());
        if (users.getPhone().length()==0){
            System.out.println("hhhhh");
        }

        String openid = users.getOpenid();
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getOpenid,openid);
        List<Users> wxUserInfos = usersMapper.selectList(queryWrapper);
        Users users1 = wxUserInfos.get(0);

        Long id = users1.getId();
        String nickName = users1.getNickName();
        String avatarUrl = users1.getAvatarUrl();
        Date registerdate = users1.getRegisterdate();
        System.out.println(users1.getPhone());
        if(users.getPhone().length()==0){
            System.out.println("进入了if");
            System.out.println(users1.getPhone());
            users.setPhone(users1.getPhone());
        }
        if (users.getSex().length()==0){
            System.out.println("进入了第二个if");
            System.out.println(users1.getSex());
            users.setSex(users1.getSex());
        }
        if (users.getAutograph().length()==0){
            System.out.println("进入了第3个if");
            users.setAutograph(users1.getAutograph());
        }
        users.setId(id);
        users.setNickName(nickName);
        users.setAvatarUrl(avatarUrl);
        users.setRegisterdate(registerdate);
        users.setLastlogindate(new Date());

        System.out.println(users);
        usersMapper.updateById(users);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminLogin(String username, String password) {
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,username);
        queryWrapper.eq(Users::getPassword,password);
        List<Users> users = usersMapper.selectList(queryWrapper);
        if (users.size()>0&&users.get(0).getType()==1){
            return ResponseResult.okResult("success");
        }
        return ResponseResult.okResult("fail");
    }

    @Override
    public ResponseResult adminGetList(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getType,0);
        Page<Users> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Users> records = page.getRecords();
        int total = (int)page.getTotal();
        List<UsersVo> usersVos = BeanCopyUtils.copyBeanList(records, UsersVo.class);
        for (UsersVo usersVo:usersVos){
            usersVo.setTotal(total);
        }
        return ResponseResult.okResult(usersVos);
    }

    @Override
    public ResponseResult adminGetById(long id) {
        Users users = usersMapper.selectById(id);
        System.out.println(id);
        System.out.println(users);
        return ResponseResult.okResult(users);
    }

    @Override
    public ResponseResult adminUpdate(Users users) {
        users.setLastlogindate(new Date());
        usersMapper.updateById(users);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminDelById(long id) {
        usersMapper.deleteById(id);
        return ResponseResult.okResult("success");
    }
}

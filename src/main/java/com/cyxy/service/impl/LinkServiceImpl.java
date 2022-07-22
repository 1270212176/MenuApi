package com.cyxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.ResponseResult;
import com.cyxy.domian.entity.Link;
import com.cyxy.domian.vo.LinkVo;
import com.cyxy.mapper.LinkMapper;
import com.cyxy.service.LinkService;
import com.cyxy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Link)表服务实现类
 *
 * @author makejava
 * @since 2022-07-08 15:38:13
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public ResponseResult getAllLinks() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus,0);
        List<Link> links = linkMapper.selectList(queryWrapper);
        return ResponseResult.okResult(links);
    }

    @Override
    public ResponseResult getAllLink(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        Page<Link> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Link> records = page.getRecords();
        int total = (int)page.getTotal();
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(records, LinkVo.class);
        for (LinkVo linkVo:linkVos){
            linkVo.setTotal(total);
        }
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult adminAddLink(Link link) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getAddress,link.getAddress());
        List<Link> links = linkMapper.selectList(queryWrapper);
        if (links.size()>0){
            return ResponseResult.okResult("fail");
        }
        linkMapper.insert(link);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult adminGetById(Integer id) {
        Link link = linkMapper.selectById(id);
        return ResponseResult.okResult(link);
    }

    @Override
    public ResponseResult adminUpdateLink(Link link) {
        linkMapper.updateById(link);
        return ResponseResult.okResult("success");
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        linkMapper.deleteById(id);
        return ResponseResult.okResult("success");
    }
}

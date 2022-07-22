package com.cyxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyxy.domian.entity.Infos;
import com.cyxy.mapper.InfosMapper;
import com.cyxy.service.InfosService;
import org.springframework.stereotype.Service;

@Service
public class InfosServiceImpl extends ServiceImpl<InfosMapper, Infos> implements InfosService {
}

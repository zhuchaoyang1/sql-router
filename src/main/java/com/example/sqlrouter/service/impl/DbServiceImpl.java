package com.example.sqlrouter.service.impl;

import com.example.sqlrouter.annotation.Master;
import com.example.sqlrouter.annotation.Slave;
import com.example.sqlrouter.dao.MyTestMapper;
import com.example.sqlrouter.entity.MyTest;
import com.example.sqlrouter.service.DbService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zcy
 * @date 2022/8/13
 * @description TODO
 */
@Repository
public class DbServiceImpl implements DbService {

    @Resource
    private MyTestMapper myTestMapper;

    @Master
    @Override
    public int save(MyTest myTest) {
        return myTestMapper.save(myTest);
    }

    @Slave
    @Override
    public MyTest queryById(Long id) {
        return myTestMapper.queryById(id);
    }
}

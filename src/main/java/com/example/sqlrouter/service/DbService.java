package com.example.sqlrouter.service;

import com.example.sqlrouter.entity.MyTest;

/**
 * @author zcy
 * @date 2022/8/13
 * @description TODO
 */
public interface DbService {

    int save(MyTest myTest);

    MyTest queryById(Long id);

}

package com.example.sqlrouter.dao;

import com.example.sqlrouter.entity.MyTest;
import org.apache.ibatis.annotations.Param;

/**
 * @author zcy
 * @date 2022/8/11
 * @description
 */
public interface MyTestMapper {

    int save(MyTest myTest);

    MyTest queryById(@Param(value = "id") long id);

}

package com.example.sqlrouter.dao;

import com.example.sqlrouter.entity.MyTest;
import com.example.sqlrouter.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class SqlRouterApplicationTests {

    @Resource
    private DbService dbService;

    @Test
    void query() {
        MyTest myTest = dbService.queryById(1L);
        log.info("{}", myTest.toString());
    }

    @Test
    void save() {
        MyTest myTest = new MyTest();
        myTest.setName("hello world");
        dbService.save(myTest);
    }

}

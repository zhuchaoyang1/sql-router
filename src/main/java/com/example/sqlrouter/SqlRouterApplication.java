package com.example.sqlrouter;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.example.sqlrouter.dao"})
@SpringBootApplication
public class SqlRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlRouterApplication.class, args);
    }

}

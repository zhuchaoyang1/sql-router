package com.example.sqlrouter.router.service;

import com.example.sqlrouter.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zcy
 * @date 2022/8/13
 * @description service sql 路由.
 */
@Slf4j
@Aspect
@Component
public class ServiceSqlRouter {

    @Pointcut(value = "@annotation(com.example.sqlrouter.annotation.Master)")
    public void masterPoint() {
    }

    @Pointcut(value = "@annotation(com.example.sqlrouter.annotation.Slave)")
    public void slavePoint() {
    }

    @Around(value = "masterPoint()")
    public Object aroundMasterExec(ProceedingJoinPoint joinPoint) throws Throwable {
        //切换数据源
        DynamicDataSourceContextHolder.setContextKey("master");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    @Around(value = "slavePoint()")
    public Object aroundSlaveExec(ProceedingJoinPoint joinPoint) throws Throwable {
        //切换数据源
        DynamicDataSourceContextHolder.setContextKey("slave1");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }


}

package com.example.sqlrouter.annotation;

import java.lang.annotation.*;

/**
 * @author zcy
 * @date 2022/8/13
 * @description TODO
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Slave {
}

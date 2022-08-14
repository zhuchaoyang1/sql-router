package com.example.sqlrouter.config;

/**
 * @author zcy
 * @date 2022/8/13
 * @description TODO
 */
public class DynamicDataSourceContextHolder {


    public static final String MASTER = "master";
    /**
     * 动态数据源名称上下文，每个线程互不影响
     */
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY = new ThreadLocal<>();

    /**
     * 设置/切换数据源
     */
    public static void setContextKey(String key) {
        DATASOURCE_CONTEXT_KEY.set(key);
    }

    /**
     * 获取数据源名称
     */
    public static String getContextKey() {
        return DATASOURCE_CONTEXT_KEY.get();
    }

    /**
     * 删除当前数据源名称
     */
    public static void removeContextKey() {
        DATASOURCE_CONTEXT_KEY.remove();
    }

}

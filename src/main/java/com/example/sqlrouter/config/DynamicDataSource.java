package com.example.sqlrouter.config;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zcy
 * @date 2022/8/13
 * @description 构建动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 该方法调用的地方：
     * {@link AbstractRoutingDataSource#determineTargetDataSource()}
     * 其内部实际就是获取本方法返回的一个对象，本系统实际上是一个字符串（master、slave）
     * 然后动态数据源内部维护了一个Map接口，根据上面的字符串作为key去该Map中获取实际的数据源
     * 最终Mybatis获取到此connect之后就可以进行执行了。
     * <p>
     * 而一切的一切实际上就是MyBatis需要获取connection才调用到此地方，调用链路如下：
     * 1. {@link SimpleExecutor#prepareStatement(org.apache.ibatis.executor.statement.StatementHandler, org.apache.ibatis.logging.Log)}
     * 2. {@link BaseExecutor#getConnection(org.apache.ibatis.logging.Log)}
     * 3. {@link SpringManagedTransaction#getConnection()}
     * 4. {@link SpringManagedTransaction#openConnection()}
     * 5. {@link DataSourceUtils#getConnection(javax.sql.DataSource)}
     * 6. {@link DataSourceUtils#doGetConnection(javax.sql.DataSource)}
     * 7. {@link DataSourceUtils#fetchConnection(javax.sql.DataSource)}
     * 8. {@link AbstractRoutingDataSource#getConnection()}
     * 9. {@link AbstractRoutingDataSource#determineTargetDataSource()}
     * <p>
     * 其实最重要的就是第1步和第9步，即：mybatis的executor组件需要执行SQL了，然后从DataSource接口实例中去获取connection对象
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }

}

# sql-router
mysql 代码层面控制读写分离

### AbstractRoutingDataSource
动态切换数据源的核心点就在此类，它是``DataSource``接口的实现类之一，常见的还有``HikariDataSource``等数据源；
而AbstractRoutingDataSource其实就是将多个其他类型的数据源捆绑在其内部的一个Map结构中，在执行的时候，根据设定动态的从Map结构中获取具体的数据源去执行SQL，以达到切换数据源的效果。

### 开发流程
- 实现AbstractRoutingDataSource路由数据源
- 注入到MyBatis的SqlSessionFactory中
- 编写业务注解，用于区分service方法走master还是slave库
- 编写AOP，拦截第三步中的注解，完成数据源的切换

### 具体调用流程
1. {@link SimpleExecutor#prepareStatement(org.apache.ibatis.executor.statement.StatementHandler, org.apache.ibatis.logging.Log)}
2. {@link BaseExecutor#getConnection(org.apache.ibatis.logging.Log)}
3. {@link SpringManagedTransaction#getConnection()}
4. {@link SpringManagedTransaction#openConnection()}
5. {@link DataSourceUtils#getConnection(javax.sql.DataSource)}
6. {@link DataSourceUtils#doGetConnection(javax.sql.DataSource)}
7. {@link DataSourceUtils#fetchConnection(javax.sql.DataSource)}
8. {@link AbstractRoutingDataSource#getConnection()}
9. {@link AbstractRoutingDataSource#determineTargetDataSource()}

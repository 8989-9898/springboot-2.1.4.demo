package com.ly.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: DataSourceConfig
 * @Author: lin
 * @Description: 数据源的配置类
 * @Date: 2019-06-18 14:08
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.ly.entity")
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Value("${spring.datasource.druid.filters}")
    private String filters;
    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private Integer maxWait;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-on-return}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
    private Integer maxPoolPreparedStatementPerConnectionSize;

    /**
     * 配置写的数据源
     * 通过 spring jdbc 创建
     *
     * @return
     */
    @Bean
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置度的数据源
     * 通过 druid 数据源配置
     *
     * @return
     * @throws SQLException
     */
    @Bean
    @Qualifier("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setFilters(filters);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return dataSource;
    }

    /**
     * 构建多数据源连接池
     *
     * @param master 写数据源使用 HikariDataSource
     * @param slave  读数据源使用 DruidDataSource
     * @return
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource master, @Qualifier("slaveDataSource") DataSource slave) {
        HashMap<Object, Object> targetMap = new HashMap<>(16);
        targetMap.put(DatabaseType.master, master);
        targetMap.put(DatabaseType.slave, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 将多个数据源 map 添加到动态数据源类中，该方法是父类的方法
        dataSource.setTargetDataSources(targetMap);
        // 设置默认的数据源为 读数据源
        dataSource.setDefaultTargetDataSource(slave);
        // 读数据源的方法规则
        String read = environment.getProperty("spring.datasource.read");
        dataSource.setMethodTypeMap(DatabaseType.slave, read);

        // 写数据源的方法规则
        String write = environment.getProperty("spring.datasource.write");
        dataSource.setMethodTypeMap(DatabaseType.master, write);
        return dataSource;
    }


    /**
     * 配置多数据源的session工厂
     *
     * @param master
     * @param slave
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource master, @Qualifier("slaveDataSource") DataSource slave) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(this.dataSource(master, slave));
        bean.setTypeAliasesPackage(environment.getProperty("mybatis.type-aliases-package"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapper-locations")));
        return bean.getObject();
    }

    /**
     * 创建多数据源的事务管理
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

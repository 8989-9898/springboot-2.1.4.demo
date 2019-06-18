package com.ly.config;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: DynamicDataSource
 * @Author: lin
 * @Description: 创建动态数据源切换
 * @Date: 2019-06-18 14:01
 * @Version: 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    static final Map<DatabaseType, List<String>> METHOD_TYPE_MAP=new HashMap<>();

    @NotNull
    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType databaseType = DatabaseContestHolder.getDatabaseType();
        System.out.println(databaseType);
        return databaseType;
    }

    public void setMethodTypeMap(DatabaseType type,String content){
        // 将方法规则设置进指定的数据源中，读写分离
        List<String> methods = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(type,methods);
    }




}

package com.ly.config;

import javax.swing.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: DatabaseContestHolder
 * @Author: lin
 * @Description: 保存一个线程安全的 DatabaseType 容器
 * @Date: 2019-06-18 13:55
 * @Version: 1.0
 */
public class DatabaseContestHolder {
    private static final ThreadLocal<DatabaseType> context=new ThreadLocal<>();

    public static DatabaseType getDatabaseType() {
        return context.get();
    }

    public static void setDatabaseType(DatabaseType type){
        context.set(type);
    }

}

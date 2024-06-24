package com.atguigu.senior.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCUtilV2
 * Package: com.atguigu.senior.util
 * Description: jdbc工具类v2
 * 1.维护一个连接池对象,维护一个线程绑定变量的ThreadLocal对象
 * 2.对外提供在threadLocal中获取连接的方法
 * 3.对外提供回收连接的方法,回收过程中将要回收的连接从threadLocal移除
 * 注意：
 * 使用ThreadLocal就是为了一个线程在多次数据库操作中使用的的是一个连接
 *
 * @Author: bushG
 * @Create: 2024/6/24 15:12
 * @Version: 1.0
 */
public class JDBCUtilV2 {
    // 创建连接池引用，因为要提供给全局使用，所以创建为静态
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadlocal = new ThreadLocal<>();

    // 在项目启动时，创建连接池对象，赋值给dataSource
    static {
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtilV2.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外提供在连接池中获取连接的方法
     *
     * @return connection对象
     */
    public static Connection getConnection() {
        try {
            Connection connection = threadlocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                threadlocal.set(connection);
            }
            return threadlocal.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回收连接
     */
    public static void release() {
        try {
            Connection connection = threadlocal.get();
            if (connection != null) {
                threadlocal.remove();
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

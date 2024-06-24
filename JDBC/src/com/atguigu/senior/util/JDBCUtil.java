package com.atguigu.senior.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCUtil
 * Package: com.atguigu.senior.util
 * Description: jdbc工具类
 * 1.维护一个连接池对象
 * 2.对外提供在连接池中获取连接的方法
 * 3.对外提供回收连接的方法
 * 注意：
 * 工具类对外提供共性的功能代码，所以方法为静态方法
 *
 * @Author: bushG
 * @Create: 2024/6/24 15:12
 * @Version: 1.0
 */
public class JDBCUtil {
    // 创建连接池引用，因为要提供给全局使用，所以创建为静态
    private static DataSource dataSource;

    // 在项目启动时，创建连接池对象，赋值给dataSource
    static {
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
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
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对外提供回收连接的方法
     *
     * @param connection 要回收的连接
     */
    public static void release(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

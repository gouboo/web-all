package com.atguigu.advanced.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ClassName: HikariTest
 * Package: com.atguigu.advanced.pool
 * Description: Hikari连接使用
 *
 * @Author: bushG
 * @Create: 2024/6/24 14:55
 * @Version: 1.0
 */
public class HikariTest {
    @Test
    public void testHardCodeHikari() throws Exception{

        //1.获取Hikari的数据库连接池对象
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///atguigu");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("123");

        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(20);

        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void testResourcesHikari() throws Exception{

        //1.创建properties集合，用于存储外部配置文件的key-value
        Properties properties = new Properties();
        //2.读取外部配置文件，获取输入流，加载到properties集合中
        InputStream inputStream = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        properties.load(inputStream);
        //3.创建HikariConfig连接池配置对象，传入properties集合
        // HikariConfig hikariConfig = new HikariConfig(properties);
        //4.创建Hikari连接池对象，基于配置对象
        HikariDataSource hikariDataSource = new HikariDataSource(new HikariConfig(properties));

        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}

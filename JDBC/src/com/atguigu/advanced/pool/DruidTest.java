package com.atguigu.advanced.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: DruidTest
 * Package: com.atguigu.advanced.pojo.polo
 * Description: druid测试
 *
 * @Author: bushG
 * @Create: 2024/6/24 14:17
 * @Version: 1.0
 */
public class DruidTest {
    @Test
    public void testHardCodeDruid() throws SQLException {
        // 1.创建druid连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();
        // 2.设置连接池属性
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql:///atguigu");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123");

        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);

        // 3.获取连接
        Connection conn = druidDataSource.getConnection();
        System.out.println(conn);
        // 4.回收
        conn.close();
    }

    @Test
    public void testResourcesDruid() throws Exception {

        // 1.创建properties集合
        Properties properties = new Properties();
        // 2.读取外部配置文件
        InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(inputStream);
        // 3.基于properties，创建druidDataSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        System.out.println(connection);

        connection.close();

    }

}

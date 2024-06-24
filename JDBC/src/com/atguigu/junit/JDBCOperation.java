package com.atguigu.junit;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName: JDBCOperation
 * Package: com.atguigu.junit
 * Description: 操作jdbc数据库
 *
 * @Author: bushG
 * @Create: 2024/6/23 21:44
 * @Version: 1.0
 */
public class JDBCOperation {
    //单行单列的查询
    @Test
    public void testQuerySingleRowAndCol() throws Exception {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.registerDriver(new Driver());
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";

        Connection conn = DriverManager.getConnection(url, username, pwd);
        // 预编译sql语句得到prepareStatement对象
        PreparedStatement preparedStatement = conn.prepareStatement("select count(*) from t_emp");
        // 执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 遍历结果集,resultSet至少要做一次resultSet.hasNext的判断
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        // 关闭资源
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testQuerySingleRow() throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.registerDriver(new Driver());
        //2. 获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        //3. 预编译sql，获取prepareStatement对象
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM t_emp where emp_id=?");
        //4.执行sql语句，给占位符赋值
        preparedStatement.setInt(1, 5);
        ResultSet resultSet = preparedStatement.executeQuery();
        //5. 获取结果
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString("emp_id") +
                            "\t" + resultSet.getString("emp_name") +
                            "\t" + resultSet.getString("emp_salary") +
                            "\t" + resultSet.getString("emp_age"));
        }
        //6. 关闭资源
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testQueryMoreRow() throws Exception{
        //获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");

        //预编译sql
        PreparedStatement preparedStatement = conn.prepareStatement("select emp_id, emp_name, emp_salary, emp_age from t_emp where emp_age> ?;");

        //执行sql,给占位符赋值
        preparedStatement.setInt(1, 25);
        ResultSet resultSet = preparedStatement.executeQuery();

        //获取结果
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString("emp_id") +
                            "\t" + resultSet.getString("emp_name") +
                            "\t" + resultSet.getString("emp_salary") +
                            "\t" + resultSet.getString("emp_age"));
        }

        //关闭连接
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testInsert() throws Exception{
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?);");
        preparedStatement.setString(1, "张三");
        preparedStatement.setDouble(2, 10000);
        preparedStatement.setInt(3, 25);
        int i = preparedStatement.executeUpdate();
        if (i>0) System.out.println("success！");
        else System.out.println("fail！");
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testUpdate() throws Exception{
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("update t_emp set emp_salary=? where emp_id = ?");
        preparedStatement.setDouble(1, 20000);
        preparedStatement.setInt(2, 6);
        int i = preparedStatement.executeUpdate();
        if (i>0) System.out.println("success！");
        else System.out.println("fail!");
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testDelete() throws Exception{
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("delete from t_emp where emp_id = ?");
        preparedStatement.setInt(1, 6);
        int i = preparedStatement.executeUpdate();
        if (i>0) System.out.println("success！");
        else System.out.println("fail!");
        preparedStatement.close();
        conn.close();
    }
}

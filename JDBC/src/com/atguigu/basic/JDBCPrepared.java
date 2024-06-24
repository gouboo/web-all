package com.atguigu.basic;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * ClassName: JDBCPrepared
 * Package: com.atguigu.basic
 * Description: preparedStatement的使用，安全、高效，避免注入
 *
 * @Author: bushG
 * @Create: 2024/6/23 21:22
 * @Version: 1.0
 */
public class JDBCPrepared {
    public static void main(String[] args) throws Exception {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.registerDriver(new Driver());
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";
        Connection conn = DriverManager.getConnection(url, username, pwd);
        // 获取安全的preparedStatement
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT emp_id, emp_name, emp_salary, emp_age FROM t_emp where emp_name=?");

        // 动态参数
        ResultSet resultSet = null;
        while (true) {
            System.out.println("输入查询姓名：");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if (s.equals("!")) break;

            // 执行sql语句
            preparedStatement.setString(1, s);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getInt(4));
            }
        }


        // 关闭资源
        if (resultSet != null) {
            resultSet.close();
        }
        preparedStatement.close();
        conn.close();
    }
}

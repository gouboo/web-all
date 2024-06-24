package com.atguigu.basic;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: JDBCInjection
 * Package: com.atguigu.basic
 * Description: sql注入
 *
 * @Author: bushG
 * @Create: 2024/6/23 21:07
 * @Version: 1.0
 */
public class JDBCInjection {
    public static void main(String[] args) throws Exception {

        // 驱动注册
        // 连接数据库
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";

        Connection conn = DriverManager.getConnection(url, username, pwd);

        // 发送sql语句
        Statement stmt = conn.createStatement();

        System.out.println("输入员工姓名");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        // abc' or '1'='1
        String s = "SELECT emp_id, emp_name, emp_salary, emp_age FROM t_emp where emp_name='" + name + "'";
        ResultSet rs = stmt.executeQuery(s);
        while (rs.next()) {
            System.out.println(
                    rs.getInt("emp_id") + "\t" +
                            rs.getString("emp_name") + "\t" +
                            rs.getDouble("emp_salary") + "\t" +
                            rs.getInt("emp_age"));
        }
        //6.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}


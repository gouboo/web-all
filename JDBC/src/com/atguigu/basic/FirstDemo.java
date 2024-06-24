package com.atguigu.basic;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ClassName: FirstDemo
 * Package: com.atguigu.basic
 * Description: 入门
 *
 * @Author: bushG
 * @Create: 2024/6/23 20:02
 * @Version: 1.0
 */
public class FirstDemo {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // DriverManager.registerDriver(new Driver());
        // jdk6之后就可以不用显示调用Class.forName()加载驱动，集成了对应的jar包，service下面有对应的驻测驱动全路径,java自动注册


        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";

        Connection conn = DriverManager.getConnection(url, username, pwd);
        //3.创建语句对象
        Statement stmt = conn.createStatement();
        //4.执行sql语句,接收返回结果集
        String s = "SELECT emp_id, emp_name, emp_salary, emp_age FROM t_emp";
        ResultSet rs = stmt.executeQuery(s);
        //5.处理结果集
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

package com.atguigu.junit;

import com.atguigu.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ClassName: JDBCAdvanced
 * Package: com.atguigu.junit
 * Description: 实体类测试
 *
 * @Author: bushG
 * @Create: 2024/6/24 12:17
 * @Version: 1.0
 */
public class JDBCAdvanced {
    @Test
    public void testORM() throws Exception {
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";

        Connection conn = DriverManager.getConnection(url, username, pwd);
        PreparedStatement preparedStatement = conn.prepareStatement("select emp_id, emp_name, emp_salary, emp_age from t_emp where emp_id= ?");
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;
        while (resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }
        System.out.println(employee);
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testORMList() throws Exception {
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String username = "root";
        String pwd = "123";

        Connection conn = DriverManager.getConnection(url, username, pwd);
        PreparedStatement preparedStatement = conn.prepareStatement("select emp_id, emp_name, emp_salary, emp_age from t_emp");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = null;
        while (resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            employees.add(employee);
        }
        employees.stream().forEach(emp -> System.out.println(emp));
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testReturnPK() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "张三丰");
        preparedStatement.setDouble(2, 10000.0);
        preparedStatement.setInt(3, 23);


        Employee employee = new Employee(null, "张三丰", 10000.0, 21);

        ResultSet resultSet = null;
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("success!");
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                employee.setEmpId(id);
                System.out.println(employee);
            }
        } else {
            System.out.println("fails!");
        }

        if (resultSet != null) {

            resultSet.close();
        }
        preparedStatement.close();
        conn.close();

    }

    @Test
    public void testMoreInsert() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)");
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "张三丰" + i);
            preparedStatement.setDouble(2, 10000.0 + i);
            preparedStatement.setInt(3, random.nextInt(100) + 18);
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testMoreInsertBatch() throws Exception{
        // 批量操作
        // 1.连接数据库添加参数?rewriteBatchedStatements=true
        // 2.sql语句不要加;
        // 3.使用preparedStatement.addBatch()方法添加sql语句
        // 4.使用preparedStatement.executeBatch()方法执行sql语句
        Connection conn = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "123");
        PreparedStatement preparedStatement = conn.prepareStatement("insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)");
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "张三丰" + i);
            preparedStatement.setDouble(2, 10000.0 + i);
            preparedStatement.setInt(3, random.nextInt(100) + 18);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        preparedStatement.close();
        conn.close();
    }
}

package com.atguigu.senior;

import com.atguigu.senior.dao.BankDao;
import com.atguigu.senior.dao.EmployeeDao;
import com.atguigu.senior.dao.impl.BankDaoImpl;
import com.atguigu.senior.dao.impl.EmployeeDaoImpl;
import com.atguigu.senior.util.JDBCUtil;
import com.atguigu.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: JDBCUtilTest
 * Package: com.atguigu.senior
 * Description: 工具类测试
 *
 * @Author: bushG
 * @Create: 2024/6/24 15:29
 * @Version: 1.0
 */
public class JDBCUtilTest {
    @Test
    public void testGetConn() {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
        JDBCUtil.release(connection);
    }

    @Test
    public void testV2() {
//        Connection connection1 = JDBCUtil.getConnection();
//        Connection connection2 = JDBCUtil.getConnection();
//        Connection connection3 = JDBCUtil.getConnection();
//        System.out.println(connection1);
//        System.out.println(connection2);
//        System.out.println(connection3);
        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
        JDBCUtilV2.release();
    }

    @Test
    public void testEmployeeDao() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
//        List<Employee> employees = employeeDao.selectAll();
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }


//        Employee employee = employeeDao.selectByEmpId(1);
//        System.out.println(employee);


//        Employee employee = new Employee(null, "jj", 300.2, 23);
//        int insert = employeeDao.insert(employee);
//        System.out.println(insert);

//        Employee employee = new Employee(20008, "jj", 3100.2, 23);
//        int update = employeeDao.update(employee);
//        System.out.println(update);

        int delete = employeeDao.delete(20008);
        System.out.println(delete);

    }

    @Test
    public void testTransaction() {
        BankDao bankDao = new BankDaoImpl();
        Connection connection = null;
        try {
            // 1.获取连接将连接给为手动提交
            connection = JDBCUtilV2.getConnection();
            connection.setAutoCommit(false);
            // 2.减钱
            bankDao.reduceMoney(1, 100);
            // 制造异常
            int i = 1 / 0;
            // 3.加钱
            bankDao.addMoney(2, 100);
            // 4.提交
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            JDBCUtilV2.release();
        }
    }
}

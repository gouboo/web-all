package com.atguigu.senior.dao.impl;

import com.atguigu.senior.dao.BaseDAO;
import com.atguigu.senior.dao.EmployeeDao;
import com.atguigu.senior.pojo.Employee;

import java.util.List;

/**
 * ClassName: EmployeeDaoImpl
 * Package: com.atguigu.senior.dao
 * Description: EmployeeDao的实现类
 *
 * @Author: bushG
 * @Create: 2024/6/24 16:09
 * @Version: 1.0
 */
public class EmployeeDaoImpl extends BaseDAO implements EmployeeDao {
    @Override
    public List<Employee> selectAll() {
        try {
            String sql = "select emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge from t_emp";
            return executeQuery(Employee.class, sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee selectByEmpId(Integer empId) {
        try {
            String sql = "select emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge from t_emp where emp_id=?";
            return executeQueryBean(Employee.class, sql, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee employee) {
        try {
            String sql = "insert into t_emp (emp_name, emp_salary, emp_age) values (?,?,?)";
            return executeUpdate(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {
        String sql = "update t_emp set emp_salary=? where emp_id=?";
        try {
            return executeUpdate(sql, employee.getEmpSalary(), employee.getEmpId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer empId) {
        String sql = "delete from t_emp where emp_id=?";
        try {
            return executeUpdate(sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

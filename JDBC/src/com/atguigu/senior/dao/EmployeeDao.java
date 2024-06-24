package com.atguigu.senior.dao;

import com.atguigu.senior.pojo.Employee;

import java.util.List;

/**
 * ClassName: EmployeeDao
 * Package: com.atguigu.senior.dao
 * Description: data access object数据访问对象
 * 对应e_emp这张表的增删改查的操作
 *
 * @Author: bushG
 * @Create: 2024/6/24 16:00
 * @Version: 1.0
 */
public interface EmployeeDao {
    /**
     * 查询所有员工信息
     *
     * @return 所有数据
     */
    List<Employee> selectAll();

    /**
     * 通过id查询单条数据
     *
     * @param empId 员工id
     * @return Employee员工对象
     */
    Employee selectByEmpId(Integer empId);

    /**
     * 新增一条员工信息
     *
     * @param employee orm思想中的一个员工对象
     * @return 受影响行数
     */
    int insert(Employee employee);

    /**
     * 修改一个员工数据
     *
     * @param employee orm思想中的一个员工对象
     * @return 受影响的行数
     */
    int update(Employee employee);

    /**
     * 删除一个员工数据
     *
     * @param empId 员工id
     * @return 受影响的行数
     */
    int delete(Integer empId);
}

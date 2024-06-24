package com.atguigu.junit;

import com.atguigu.advanced.pojo.Employee;
import org.junit.Test;

/**
 * ClassName: LombokTest
 * Package: com.atguigu.junit
 * Description: lombok测试
 *
 * @Author: bushG
 * @Create: 2024/6/24 12:09
 * @Version: 1.0
 */
public class LombokTest {
    @Test
    public void testAll() {
        Employee emp1 = new Employee();
        Employee emp2 = new Employee(1, "John", 123.123, 18);
        emp2.getEmpId();
        emp2.getEmpAge();
        emp2.getEmpName();
        emp2.getEmpSalary();
        emp2.setEmpAge(18);
        emp2.setEmpId(1);
        emp2.setEmpName("John1");
        emp2.setEmpSalary(123.123);
        System.out.println(emp2);
    }
}

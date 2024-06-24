package com.atguigu.advanced.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Employee
 * Package: com.atguigu.advanced.pojo
 * Description: 实体类搭建
 *
 * @Author: bushG
 * @Create: 2024/6/24 12:05
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
}

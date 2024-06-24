package com.atguigu.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: SysSchedule
 * Package: com.atguigu.schedule.pojo
 * Description: sys_schedulel库实体类
 *
 * @Author: bushG
 * @Create: 2024/6/23 13:53
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSchedule implements Serializable {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;
    // 实体类的类名和表格名称应该对应
    // 实体类的属性名和表格的列名应该对应
    // 每个属性必须是私有的
    // 属性都得有getter、setter方法
    // 实体类必须实现Serializable接口
    // 具备无参构造器
    // 重写hashcode、equals、toString
}
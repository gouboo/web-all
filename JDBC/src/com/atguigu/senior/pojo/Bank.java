package com.atguigu.senior.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Bank
 * Package: com.atguigu.senior.pojo
 * Description: 实现类
 *
 * @Author: bushG
 * @Create: 2024/6/24 19:26
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank {
    private Integer id;
    private String account;
    private Integer money;
}

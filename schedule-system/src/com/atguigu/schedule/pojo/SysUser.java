package com.atguigu.schedule.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * ClassName: SysUser
 * Package: com.atguigu.schedule.schedule.pojo
 * Description: 数据库操作实体类
 *
 * @Author: bushG
 * @Create: 2024/6/23 13:14
 * @Version: 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
//以上注释四个可以合并为下面
@Data
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;

}

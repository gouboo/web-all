package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysUser;

/**
 * ClassName: SysUserDao
 * Package: com.atguigu.schedule.dao
 * Description: sys_user DAO
 *
 * @Author: bushG
 * @Create: 2024/6/23 13:56
 * @Version: 1.0
 */

/*
* data access object数据访问对象
* 定义针对表格的crud的方法
* DAO层一般需要定义接口和实现类
* */
public interface SysUserDao {
    /**
     * 向数据库中增加一条用户记录的方法
     * @param sysUser 要记录的name和pwd字段以SysUser实体类对象的形式接收
     * @return 增加成功返回1，失败返回0
     */
    int addSysUser(SysUser sysUser);

    /**
     * 根据用户名获得完整信息的方法
     *
     * @param username 要查询的用户名
     * @return 找到了返回SysUser对象，否则返回null
     */
    SysUser findByUsername(String username);
}

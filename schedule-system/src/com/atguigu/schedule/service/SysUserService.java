package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysUser;

/**
 * ClassName: SysUserService
 * Package: com.atguigu.schedule.service
 * Description: 该接口定义了以sys_user表格为核心的业务处理功能
 *
 * @Author: bushG
 * @Create: 2024/6/25 0:48
 * @Version: 1.0
 */
public interface SysUserService {
    /**
     * 注册用户方法
     *
     * @param sysUser 要注册的用户名和明文密码，以sysUser对象的形式接收
     * @return 注册成功返回1 注册失败返回0
     */
    int regist(SysUser sysUser);

    /**
     * 根据用户名获得完整信息的方法
     *
     * @param username 要查询的用户名
     * @return 找到了返回SysUser对象，否则返回null
     */
    SysUser findByUsername(String username);
}

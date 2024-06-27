package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDAO;
import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.pojo.SysUser;

import java.util.List;

/**
 * ClassName: SysUserDaoImpl
 * Package: com.atguigu.schedule.dao.impl
 * Description: 接口实现类
 *
 * @Author: bushG
 * @Create: 2024/6/23 14:03
 * @Version: 1.0
 */
public class SysUserDaoImpl extends BaseDAO implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return executeUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid, username, user_pwd userPwd from sys_user where username=?";
        return executeQueryBean(SysUser.class, sql, username);
    }
}

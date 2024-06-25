package com.atguigu.schedule.test;

import com.atguigu.schedule.dao.BaseDAO;
import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.pojo.SysUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * ClassName: TestBaseDAO
 * Package: com.atguigu.schedule.test
 * Description: baseDao测试类
 *
 * @Author: bushG
 * @Create: 2024/6/24 21:35
 * @Version: 1.0
 */
public class TestBaseDAO {
    private static BaseDAO baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDAO();
    }

    @Test
    public void testExecuteQuery() {
        String sql = "select uid, username, user_pwd userPwd from sys_user";
        try {
            List<SysUser> sysUser = baseDao.executeQuery(SysUser.class, sql);
            sysUser.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExecuteQueryBean() throws Exception {
        String sql = "select count(*) from sys_user";
        Long l = baseDao.executeQueryBean(Long.class, sql);
        System.out.println(l);
    }
    
    @Test
    public void testExecuteUpdate() throws Exception {
        String sql = "insert into sys_schedule values(default,?,?,?)";
        int i = baseDao.executeUpdate(sql, 1, "学习java", 0);
        System.out.println(i);
    }
}


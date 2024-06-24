package com.atguigu.schedule.test;

import com.atguigu.schedule.dao.BaseDAO;
import com.atguigu.schedule.dao.SysUserDao;
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
    public void testQueryExecute() {
        String sql = "select count(*) from sys_user";
        try {
            Long l = baseDao.executeQueryBean(Long.class, sql);
            System.out.println(l);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test1() throws Exception {

        Field asdf = SysUserDao.class.getDeclaredField("asdf");
    }
}


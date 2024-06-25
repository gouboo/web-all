package com.atguigu.schedule.test;

import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.dao.impl.SysScheduleDaoImpl;
import com.atguigu.schedule.pojo.SysSchedule;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: TestSysScheduleDao
 * Package: com.atguigu.schedule.test
 * Description: 接口实现类测试
 *
 * @Author: bushG
 * @Create: 2024/6/25 0:35
 * @Version: 1.0
 */
public class TestSysScheduleDao {
    private static SysScheduleDao scheduleDao;

    @BeforeClass
    public static void initScheduleDao() {
        scheduleDao = new SysScheduleDaoImpl();
    }

    @Test
    public void testAddSchedule() {

        int i = scheduleDao.addSchedule(new SysSchedule(null, 2, "学习数据库", 1));
        System.out.println(i);
    }

    @Test
    public void testFindAll(){
        List<SysSchedule> scheduleList = scheduleDao.findAll();
        scheduleList.forEach(System.out::println);

    }
}

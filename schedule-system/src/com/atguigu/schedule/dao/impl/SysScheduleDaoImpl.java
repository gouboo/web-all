package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDAO;
import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * ClassName: SysScheduleDaoImpl
 * Package: com.atguigu.schedule.dao.impl
 * Description: 接口实现类
 *
 * @Author: bushG
 * @Create: 2024/6/23 14:04
 * @Version: 1.0
 */
public class SysScheduleDaoImpl extends BaseDAO implements SysScheduleDao {

    @Override
    public int addSchedule(SysSchedule schedule) {
        String sql = "insert into sys_schedule values (default,?,?,?)";
        return executeUpdate(sql, schedule.getUid(), schedule.getTitle(), schedule.getCompleted());
    }

    @Override
    public List<SysSchedule> findAll() {
        String sql = "select sid,uid,title,completed from sys_schedule";
        return executeQuery(SysSchedule.class, sql);
    }
}

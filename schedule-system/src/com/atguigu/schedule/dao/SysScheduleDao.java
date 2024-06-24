package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

/**
 * ClassName: SysScheduleDao
 * Package: com.atguigu.schedule.dao
 * Description: sys_schedule Dao接口
 *
 * @Author: bushG
 * @Create: 2024/6/23 13:58
 * @Version: 1.0
 */
public interface SysScheduleDao {
    /**
     * 用户向数据中增加一条日程记录
     *
     * @param schedule 日程数据以SysSchedule实体类对象形式入参
     * @return 返回影响数据行数，0表示增加失败，大于0表示成功
     */
    int addSchedule(SysSchedule schedule);
}

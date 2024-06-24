package com.atguigu.senior.dao.impl;

import com.atguigu.senior.dao.BankDao;
import com.atguigu.senior.dao.BaseDAO;

/**
 * ClassName: BankDaoImpl
 * Package: com.atguigu.senior.dao.impl
 * Description: bankdao实现类
 *
 * @Author: bushG
 * @Create: 2024/6/24 19:30
 * @Version: 1.0
 */
public class BankDaoImpl extends BaseDAO implements BankDao {
    @Override
    public int addMoney(Integer id, Integer money) {
        String sql = "update t_bank set money = money+ ? where id = ?";
        try {
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int reduceMoney(Integer id, Integer money) {
        String sql = "update t_bank set money = money- ? where id =?";
        try {
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

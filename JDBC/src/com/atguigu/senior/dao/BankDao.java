package com.atguigu.senior.dao;

/**
 * ClassName: BankDao
 * Package: com.atguigu.senior.dao
 * Description: 银行Dao接口
 *
 * @Author: bushG
 * @Create: 2024/6/24 19:27
 * @Version: 1.0
 */
public interface BankDao {
    /**
     * 账户余额增加
     *
     * @param id    账户id
     * @param money 增加的钱数
     * @return 受影响的行数
     */
    int addMoney(Integer id, Integer money);

    /**
     * 账户余额减少
     *
     * @param id    账户id
     * @param money 减少的钱数
     * @return 受影响的行数
     */
    int reduceMoney(Integer id, Integer money);
}

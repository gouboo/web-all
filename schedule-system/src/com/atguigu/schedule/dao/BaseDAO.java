package com.atguigu.schedule.dao;

import com.atguigu.schedule.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

;

/**
 * ClassName: BaseDAO
 * Package: com.atguigu.senior.dao
 * Description: 将共性的数据库操作代码封装在该类中
 *
 * @Author: bushG
 * @Create: 2024/6/24 16:14
 * @Version: 1.0
 */
public class BaseDAO {
    /**
     * 通用的增删改的方法。
     *
     * @param sql    要执行的sql
     * @param params 占位符的参数
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object... params) {

        // 1.通过JDBCUtilV2获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 2.预编译sql语句
        PreparedStatement preparedStatement = null;
        int row = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 3.占位符赋值

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            // 4.执行sql语句
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            try {
                preparedStatement.close();
                if (connection.getAutoCommit()) {
                    JDBCUtil.release();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return row;
    }

    //封装过程：
    //1.返回的类型：泛型，类型不确定，调用者知道，调用时，将此次查询的结果类型告诉BaseDAO就可以
    //2.返回的结果：list，可以存储多个结果，也可以存储一个结果
    //3.结果的封装：反射！，要求调用者告诉baseDao要封装对象的类对象
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) {
        // 1.通过JDBCUtilV2获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 2.预编译sql语句
        List<T> arrayList = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 3.占位符赋值

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            // 4.执行sql语句
            resultSet = preparedStatement.executeQuery();
            // 获取结果集中的元数据对象，包含列数量，列名称
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(columnLabel);
                    // 处理datetime类型的字段和java.util.Data转换问题
                    if (value.getClass().equals(LocalDateTime.class)) {
                        value = Timestamp.valueOf((LocalDateTime) value);
                    }

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(obj, value);
                }
                arrayList.add((T) obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源

            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                if (connection.getAutoCommit()) {
                    JDBCUtil.release();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return arrayList;
    }

    public <T> T executeQueryBean(Class<T> clazz, String sql, Object... params) {

        List<T> list = this.executeQuery(clazz, sql, params);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    public <T> T executeQuerySingleRowClo(Class<T> clazz, String sql, Object... params) {
        T t = null;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) t = (T) resultSet.getObject(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != resultSet) resultSet.close();
                if (null != preparedStatement) preparedStatement.close();
                if (connection.getAutoCommit()) {
                    JDBCUtil.release();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }
}

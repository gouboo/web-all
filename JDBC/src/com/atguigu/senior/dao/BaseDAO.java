package com.atguigu.senior.dao;

import com.atguigu.senior.util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

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
    public int executeUpdate(String sql, Object... params) throws Exception {

        // 1.通过JDBCUtilV2获取数据库连接
        Connection connection = JDBCUtilV2.getConnection();
        // 2.预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 3.占位符赋值
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        // 4.执行sql语句
        int row = preparedStatement.executeUpdate();
        // 5.关闭资源
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtilV2.release();
        }
        return row;
    }

    //封装过程：
    //1.返回的类型：泛型，类型不确定，调用者知道，调用时，将此次查询的结果类型告诉BaseDAO就可以
    //2.返回的结果：list，可以存储多个结果，也可以存储一个结果
    //3.结果的封装：反射！，要求调用者告诉basedao要封装对象的类对象
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws Exception {
        // 1.通过JDBCUtilV2获取数据库连接
        Connection connection = JDBCUtilV2.getConnection();
        // 2.预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 3.占位符赋值
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        // 4.执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 获取结果集中的元数据对象，包含列数量，列名称
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        ArrayList<T> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object value = resultSet.getObject(i + 1);
                // 获取当前拿到列的名字 = 属性名
                String columnLabel = metaData.getColumnLabel(i + 1);
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t, value);
            }
            arrayList.add(t);
        }
        // 5.关闭资源
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtilV2.release();
        }
        return arrayList;
    }


    public <T> T executeQueryBean(Class<T> clazz, String sql, Object... params) throws Exception {
        List<T> ts = executeQuery(clazz, sql, params);
        if (ts != null && !ts.isEmpty()) {
            return ts.get(0);
        }
        return null;
    }
}

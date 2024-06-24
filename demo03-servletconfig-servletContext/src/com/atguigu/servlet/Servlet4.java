package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: Servlet4
 * Package: com.atguigu.servlet
 * Description: httpServletRequest常见api（参数）
 *
 * @Author: bushG
 * @Create: 2024/6/22 11:12
 * @Version: 1.0
 */
@WebServlet("/servlet4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取单个参数值
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        //2.获取多个参数值
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(Arrays.toString(hobbies));
        //3.获取所有参数名
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            String[] values = req.getParameterValues(s);
            if (values.length > 1) System.out.println(s + "=" + Arrays.toString(values));
            else System.out.println(s + "=" + values[0]);
        }
        // 返回所有参数的map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values.length > 1) System.out.println(key + "=" + Arrays.toString(values));
            else System.out.println(key + "=" + values[0]);
        }


        /*
        * 获取kv形式参数，无论是在url还是请求体中，以上都可以用 get、post都可以
        *
        * form表单标签get提交时，参数以键值对形式放在url后，无论参数在哪（请求体、或者url后）
        *
        *
        * 获得请求体中的非键值对数据json、文件
        * */
        // 获取一个从请求体重读取字符的字符输入流(json)
//        BufferedReader reader = req.getReader();
        // 获取一个从请求体中读取字节的字节输入流（文件）
//        ServletInputStream inputStream = req.getInputStream();
        // 获取请求路径
        String servletPath = req.getServletPath();
    }
}

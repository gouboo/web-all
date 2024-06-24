package com.atguigu.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * ClassName: com.atguigu.servlet.Servlet1
 * Package: PACKAGE_NAME
 * Description: servletconfig和servletcontext简介
 *
 * @Author: bushG
 * @Create: 2024/6/21 14:43
 * @Version: 1.0
 */
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        String keya = servletConfig.getInitParameter("keya");
        System.out.println("keya=" + keya);
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
//        判断有没有下一个参数，有true没有false
        while (initParameterNames.hasMoreElements()) {
            String pname = initParameterNames.nextElement();
            System.out.println(pname + "\t" + servletConfig.getInitParameter(pname));
        }

        System.out.println("---------------------ServletContext获取参数---------------------------");
        ServletContext servletContext3 = getServletContext();
        ServletContext servletContext2 = getServletConfig().getServletContext();
        ServletContext servletContext1 = req.getServletContext();
        System.out.println(servletContext1 == servletContext2 && servletContext1 == servletContext3);

        String encoding = servletContext1.getInitParameter("encoding");
        System.out.println("encoding=" + encoding);

        Enumeration<String> pname = servletContext1.getInitParameterNames();
        while (pname.hasMoreElements()) {
            String s = pname.nextElement();
            System.out.println(s + "\t" + servletContext1.getInitParameter(s));
        }
//获取一个指向项目部署位置下的某个文件、目录的磁盘真实路径
        String realPath = servletContext1.getRealPath("upload");

//        获取项目上下文路径，项目的访问路径
        String contextPath = servletContext1.getContextPath();

//      域对象：一些用于存储数据和传递数据的对象，域对象是有范围区分的
//        webapp中的三大域对象，应用域、会话域、请求域
//        servletContext代表应用域，是最大的域，可以在本应用内实现数据的共享和传递，servlet1和2都可以识别到
//      向域中存储、修改数据
        servletContext1.setAttribute("test", "test1");
//        获取域中数据
        Object test = servletContext1.getAttribute("test");
//        移除域中的数据
        servletContext1.removeAttribute("test");

    }
}

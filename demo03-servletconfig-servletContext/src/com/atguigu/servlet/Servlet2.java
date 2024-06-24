package com.atguigu.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(
        urlPatterns = "/servlet2",
        initParams = {
                @WebInitParam(name = "keya", value = "value2a"),
                @WebInitParam(name = "keyb", value = "value2b")
        }
)
public class Servlet2 extends HttpServlet {
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

//        读取域对象中的数据
        String test = (String) servletContext1.getAttribute("test");
        System.out.println(test);

    }
}

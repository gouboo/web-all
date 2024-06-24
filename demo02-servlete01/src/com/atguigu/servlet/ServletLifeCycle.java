package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletLifeCycle
 * Package: com.atguigu.servlet
 * Description: servlet的生命周期
 *
 * @Author: bushG
 * @Create: 2024/6/21 12:06
 * @Version: 1.0
 */
@WebServlet("/servletLifeCycle")
public class ServletLifeCycle extends HttpServlet {
    public ServletLifeCycle() {
        System.out.println("构造器");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("服务");
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}

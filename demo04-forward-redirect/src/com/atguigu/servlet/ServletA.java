package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletA
 * Package: com.atguigu.servlet
 * Description: forward请求转发的相关概念
 *
 * @Author: bushG
 * @Create: 2024/6/22 12:10
 * @Version: 1.0
 */
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    /*
     * 1.请求转发是通过httpServletRequest对象获取请求转发器实现的
     * 2.请求转发是服务器内部行为，对客户端是屏蔽的
     * 3.客户端只产生一次请求，服务端只产生一对req和resp
     * 4.客户端的地址栏不变
     * 5.请求的参数可以继续传递
     * 6.目标资源可以是servlet动态资源，也可以是html静态资源
     * 7.目标资源可以是web-inf下面的受保护资源，该方式也是唯一方式访问受保护资源
     * 8.目标资源不可以是外部资源
     *
     *
     * */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servletA执行");
        System.out.println("servletA获得参数money：" + req.getParameter("money"));
        // 获得请求转发器，并让请求转发器做出转发动作
        // req.getRequestDispatcher("servletB").forward(req, resp);
        // req.getRequestDispatcher("a.html").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/b.html").forward(req, resp);
    }
}

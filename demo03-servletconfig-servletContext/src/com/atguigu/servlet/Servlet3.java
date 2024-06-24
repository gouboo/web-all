package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * ClassName: Servlet3
 * Package: com.atguigu.servlet
 * Description: httpServletRequest常见api
 *
 * @Author: bushG
 * @Create: 2024/6/22 10:50
 * @Version: 1.0
 */
@WebServlet("/servlet3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 行相关 get/post uir http/1.1
        System.out.println(req.getMethod());
        System.out.println(req.getScheme()); //http
        System.out.println(req.getProtocol()); //http/1.1
        System.out.println(req.getRequestURI()); // 资源路径,标识符
        System.out.println(req.getRequestURL()); // 定位符

        System.out.println(req.getLocalPort()); // 本应用容器端口号
        System.out.println(req.getServerPort()); // 客户端请求的端口号（可能有代理）
        System.out.println(req.getRemotePort()); // 客户端本地通过哪个端口号发请求


        // 头相关 kv结构
        // 根据名字获取某个头
        System.out.println("accept:" + req.getHeader("Accept"));
        // 获取所有头
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            System.out.println(s + "\t" + req.getHeader(s));
        }
    }
}

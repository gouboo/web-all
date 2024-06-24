package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: Servlet5
 * Package: com.atguigu.servlet
 * Description: httpServletReponse常见api
 *
 * @Author: bushG
 * @Create: 2024/6/22 11:46
 * @Version: 1.0
 */

@WebServlet("/servlet5")
public class Servlet5 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //响应行
        resp.setStatus(200);
        String info = "<h1>张三丰</h1>";
        //响应头
        resp.setHeader("key", "value");
        resp.setContentType("text/html");
        resp.setContentLength(info.getBytes().length);
        // 获得一个向响应体中输入字符串信息的字符输出流
        resp.getWriter().write(info);
        // 获得一个向响应体中输入二进制信息的字节输出流
        resp.getOutputStream().write(info.getBytes());

    }
}

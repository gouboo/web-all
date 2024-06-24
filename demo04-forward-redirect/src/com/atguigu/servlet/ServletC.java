package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletC
 * Package: com.atguigu.servlet
 * Description: 测试响应体编码
 *
 * @Author: bushG
 * @Create: 2024/6/22 14:46
 * @Version: 1.0
 */
@WebServlet("/servletC")
public class ServletC extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置响应体的编码字符集和客户端保持一致，不推荐，无法预测客户端解析字符集
        // 2.可以告诉客户端使用指定字符集进行编码，通过设置content-type响应头
        // 3.注意，明确响应体的编码，然后再设置客户端的解码，保证两者一致
        //设置响应体的编码格式
        resp.setCharacterEncoding("UTF-8");
        //设置响应头的解码格式，告诉客户端用xx解码
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("你好");
    }
}

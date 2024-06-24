package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: UserServlet
 * Package: com.atguigu.servlet
 * Description: say hello
 *
 * @Author: bushG
 * @Create: 2024/6/21 10:06
 * @Version: 1.0
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        servlet开发流程
//        1.创建javaWeb项目，同时将tomcat添加为当前项目依赖
//        2.重写service方法，service（HttpServletRequest req, HttpServletResponse resp）
//        3.service方法中定义业务代码
//        4。在web.xml中，配置servlet对应的请求映射路径
//        注意
//        1.servlet-api.jar在编码需要，运行的时候，在服务器的环境中，由服务软件tomcat提供
//          因此，javaWeb项目在打包构建的时候，是无需携带servlet-api的jar包
//        2.content-type响应头，是mime类型响应头，用于告诉客户端响应体的数据是什么类型，以此来决定用什么方式解析响应体，
//          servlet由于是动态资源无法通过conf.xml找到对应的content-type，需要手动设置

//        1.从request对象中获取请求中的任何信息
        String username = req.getParameter("username");

//        2.处理业务代码
        String info = "yes";
        if ("atguigu".equals(username)) {
            info = "no";
        }
//        3.响应的数据放入response
//        设置content-type
//        resp.setHeader("content-type", "text/html");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter(); // 该方法返回的是一个向响应体中打印字符串的打印流
        writer.write(info);

    }
}

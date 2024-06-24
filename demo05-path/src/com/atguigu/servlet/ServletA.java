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
 * Description: path演示
 *
 * @Author: bushG
 * @Create: 2024/6/22 22:41
 * @Version: 1.0
 */
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        重定向到servletB
//        相对路径（和前端相对规则一样）
//        绝对路径
//        出发点：http://localhost:8080
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/servletB");
//        resp.sendRedirect("/demo05/servletB");


//        请求转发到servletB
//        相对路径一致
//        绝对路径:请求转发的绝对路径不需要添加项目上下文
//        /表示的路径是http://localhost:8080/demo05
//

        req.getRequestDispatcher("/servletB").forward(req, resp);
//        项目上下文通常 配置为/那么所有路径都写为绝对路径
    }
}

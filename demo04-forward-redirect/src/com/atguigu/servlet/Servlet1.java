package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: Servlet1
 * Package: com.atguigu.servlet
 * Description: 相应重定向相关实现
 *
 * @Author: bushG
 * @Create: 2024/6/22 12:58
 * @Version: 1.0
 */
@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    /*
    * 1.响应重定向通过httpServletResponse对象的sendRedirect方法实现
    * 2.响应重定向通过302和location告诉客户端自启找资源，是服务端提示下的客户端行为
    * 3.客户端至少发送两次请求，地址栏发生改变
    * 4.服务端产生了多对请求和响应对象，且这对对象不会传递给下一个资源
    * 5.请求参数不可传递，请求域中的数据不可传递
    * 6.重定向可以是servlet、也可以静态资源
    * 7.不可以重定向到web-inf下的受保护资源
    * 8.可以重定向到外部资源
    * 实现页面跳转的情况下，优先使用重定向
    *
    *
    * get、post乱码问题，都受到charset影响，charset设置和后端需要一致，才能避免乱码问题
    *
    * post提交
    * req.setCharacterEncoding("")用来设置tomcat解码请求内容的字符集（默认u8）
    * get提交,针对请求行生效
    * server.xml connector URIEncoding=""
    * */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户请求
        System.out.println("servlet1执行了");
        System.out.println("servlet1 参数 money=" + req.getParameter("money"));
        //响应重定向，设置响应状态码302，设置location响应头
        // resp.sendRedirect("servlet2");
        // 重定向资源可以是试图资源
        // resp.sendRedirect("a.html");
        // resp.sendRedirect("WEB-INF/b.html");
        resp.sendRedirect("http://www.baidu.com");
    }
}

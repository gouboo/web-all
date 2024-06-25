package com.atguigu.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ClassName: BaseController
 * Package: com.atguigu.schedule.service
 * Description: 提取重复代码，降低冗余
 *
 * @Author: bushG
 * @Create: 2024/6/25 1:27
 * @Version: 1.0
 */
public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断请求做什么事 增删改查？
        String requestURI = req.getRequestURI();
        String methodName = requestURI.isEmpty() ?
                "" : requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

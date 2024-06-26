package com.atguigu.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: SysUserController
 * Package: com.atguigu.schedule.controller
 * Description: sys_user的servlet控制器类
 *
 * @Author: bushG
 * @Create: 2024/6/25 0:54
 * @Version: 1.0
 */
@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }

    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");

    }

    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");

    }


}

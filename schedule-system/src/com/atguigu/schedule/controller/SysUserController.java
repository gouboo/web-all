package com.atguigu.schedule.controller;

import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.util.MD5Util;
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
@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private SysUserService userService = new SysUserServiceImpl();

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收客户端提交参数
        String username = req.getParameter("username");
        String password = req.getParameter("userPwd");
        // 2.调用服务层方法，完成注册功能

        // 将属性封装进对象
        SysUser sysUser = new SysUser(null, username, password);

        int row = userService.regist(sysUser);

        // 3.根据注册结果，做页面跳转
        if (row > 0) {
            resp.sendRedirect("/registSuccess.html");
        } else {
            resp.sendRedirect("/registFail.html");
        }
    }

    /**
     * 接收登录请求，完成登录接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收客户端提交参数
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");

        // 2.调用服务层方法，根据姓名查询信息
        SysUser loginUser = userService.findByUsername(username);

        if (null == loginUser) {
            resp.sendRedirect("/loginUsernameError.html");
        } else if (!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd())) {
            // 3.判断密码是否匹配
            resp.sendRedirect("/loginPwdError.html");
        } else {
            // 4.跳转首页
            resp.sendRedirect("/showSchedule.html");
        }
    }
}

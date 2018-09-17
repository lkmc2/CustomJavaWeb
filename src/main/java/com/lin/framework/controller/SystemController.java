package com.lin.framework.controller;

import com.lin.framework.annotation.Action;
import com.lin.framework.annotation.Controller;
import com.lin.framework.bean.Param;
import com.lin.framework.bean.View;
import com.lin.framework.helper.SecurityHelper;
import com.lin.framework.plugin.security.exception.AuchcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 处理系统请求
 */
@Controller
public class SystemController {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    /**
     * 进入首页界面
     */
    @Action("get:/")
    public View index() {
        return new View("index.jsp");
    }

    /**
     * 进入登陆界面
     */
    @Action("get:/login")
    public View login() {
        return new View("login.jsp");
    }

    /**
     * 提交登陆表单
     */
    @Action("post:/login")
    public View loginSubmit(Param param) {
        String username = param.getString("username");
        String password = param.getString("password");

        try {
            // 登陆
            SecurityHelper.login(username, password);
        } catch (AuchcException e) {
            LOGGER.error("登陆失败", e);
            return new View("/login");
        }
        return new View("/customer");
    }

    /**
     * 提交注销请求
     */
    @Action("get:/logout")
    public View logout() {
        // 注销登陆
        SecurityHelper.logout();
        return new View("/");
    }


}

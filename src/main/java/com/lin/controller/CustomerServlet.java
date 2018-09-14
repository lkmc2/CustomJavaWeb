package com.lin.controller;

import com.lin.model.Customer;
import com.lin.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 客户Servlet
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    // 客户服务
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户列表
        List<Customer> customerList = customerService.getCustomerList();

        // 设置用户列表到request中
        req.setAttribute("customerList", customerList);
        // 进行页面跳转
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }

}

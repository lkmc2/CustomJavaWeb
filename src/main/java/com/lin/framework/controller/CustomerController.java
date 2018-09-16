package com.lin.framework.controller;

import com.lin.framework.annotation.Action;
import com.lin.framework.annotation.Controller;
import com.lin.framework.annotation.Inject;
import com.lin.framework.bean.View;
import com.lin.framework.service.CustomerService;
import com.lin.model.Customer;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 客户控制器
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入客户列表界面
     */
    @Action("get:/customer01")
    public View index() {
        // 获取用户列表
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

}

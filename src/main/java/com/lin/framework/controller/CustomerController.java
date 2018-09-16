package com.lin.framework.controller;

import com.lin.framework.annotation.Action;
import com.lin.framework.annotation.Controller;
import com.lin.framework.annotation.Inject;
import com.lin.framework.bean.Data;
import com.lin.framework.bean.FileParam;
import com.lin.framework.bean.Param;
import com.lin.framework.bean.View;
import com.lin.framework.service.CustomerService;
import com.lin.model.Customer;

import java.util.List;
import java.util.Map;

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
    @Action("get:/customer")
    public View index() {
        // 获取用户列表
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 处理创建客户请求
     * @param param 请求参数
     * @return 返回数据对象
     */
    @Action("post:/create_customer")
    public Data createSubmit(Param param) {
        Map<String, Object> fieldMap = param.getFieldMap();
        // 获取文件参数
        FileParam fileParam = param.getFile("photo");

        // 创建客户
        boolean result = customerService.createCustomer(fieldMap, fileParam);
        return new Data(result);
    }

}

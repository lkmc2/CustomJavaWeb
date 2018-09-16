package com.lin.service;

import com.lin.framework.helper.DatabaseHelper;
import com.lin.framework.service.CustomerService;
import com.lin.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 客户服务单元测试
 */
public class CustomerServiceTest {

    @Before
    public void init() {
        // 初始化数据库信息（每次运行都会重置，id将重新从1开始）
        DatabaseHelper.executeSqlFile("sql/customer_init.sql");
    }

    // 客户服务
    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Test
    public void getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList();
        assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomer() {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        assertNotNull(customer);
    }

    @Test
    public void createCustomer() {
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "Jahn");
        fieldMap.put("telephone", "13456785599");

        boolean result = customerService.createCustomer(fieldMap);
        assertTrue(result);
    }

    @Test
    public void updateCustomer() {
        long id = 1;
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        fieldMap.put("remark", "Forever");

        boolean result = customerService.updateCustomer(id, fieldMap);
        assertTrue(result);
    }

    @Test
    public void deleteCustomer() {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        assertTrue(result);
    }

}
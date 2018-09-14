package com.lin.service;

import com.lin.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 客户服务
 */
public class CustomerService {

    /**
     * 获取客户列表
     * @param keyword 关键字
     * @return 客户列表
     */
    public List<Customer> getCustomerList(String keyword) {
        // todo
        return null;
    }

    /**
     * 获取客户
     * @param id 客户id
     * @return 指定id的客户
     */
    public Customer getCustomer(long id) {
        // todo
        return null;
    }

    /**
     * 创建客户
     * @param fieldMap 存储用户信息的map
     * @return 是否创建成功
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        // todo
        return false;
    }

    /**
     * 更新客户信息
     * @param id 客户id
     * @param fieldMap 存储用户信息的map
     * @return 是否更新成功
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        // todo
        return false;
    }

    /**
     * 删除客户信息
     * @param id 客户id
     * @return 是否删除成功
     */
    public boolean deleteCustomer(long id) {
        // todo
        return false;
    }

}

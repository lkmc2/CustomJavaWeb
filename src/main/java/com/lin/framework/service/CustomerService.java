package com.lin.framework.service;

import com.lin.framework.annotation.Service;
import com.lin.framework.annotation.Transaction;
import com.lin.framework.helper.DatabaseHelper;
import com.lin.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 客户服务
 */
@Service
public class CustomerService {

    /**
     * 获取客户列表
     * @return 客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";

        // 查询实体列表
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     * @param id 客户id
     * @return 指定id的客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        // 查询实体
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     * @param fieldMap 存储用户信息的map
     * @return 是否创建成功
     */
    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户信息
     * @param id 客户id
     * @param fieldMap 存储用户信息的map
     * @return 是否更新成功
     */
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户信息
     * @param id 客户id
     * @return 是否删除成功
     */
    @Transaction
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }

}

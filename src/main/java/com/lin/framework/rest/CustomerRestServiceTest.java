package com.lin.framework.rest;

import com.lin.model.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 客户REST服务单元测试
 */
public class CustomerRestServiceTest {

    @Test
    public void getCustomerTest() {
        String wsdl = "http://localhost:8080/rest/CustomerRestSercie";

        // 创建客户REST服务
        CustomerRestService customerRestService = RestHelper.createClient(wsdl, CustomerRestService.class);

        // 调用接口服务，获取id为1的用户
        Customer customer = customerRestService.getCustomer(1);

        Assert.assertNotNull(customer);
    }

}

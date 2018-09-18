package com.lin.test;

import com.lin.framework.soap.CustomerSoapService;
import com.lin.framework.soap.SoapHelper;
import com.lin.model.Customer;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 客户服务
 */
public class CustomerSoapServiceTest {

    @Test
    public void getCustomerTest() {
        String wsdl = "http://localhsot:8080/soap/CustomerSoapService";

        // 生成客户SOAP服务
        CustomerSoapService customerSoapService = SoapHelper.createClient(wsdl, CustomerSoapService.class);

        // 调用服务接口，获取id为1的用户
       Customer customer = customerSoapService.getCustomer(1);

        assertNotNull(customer);
    }

}

package com.lin.framework.soap;

import com.lin.framework.annotation.Inject;
import com.lin.framework.annotation.Service;
import com.lin.framework.service.CustomerService;
import com.lin.model.Customer;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 客户SOAP服务接口实现
 */
@Soap
@Service
public class CustomerSoapServiceImpl implements CustomerSoapService {

    @Inject
    private CustomerService customerService;

    @Override
    public Customer getCustomer(long customerId) {
        return customerService.getCustomer(customerId);
    }

}

package com.lin.framework.soap;

import com.lin.model.Customer;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 客户SOAP接口服务
 */
public interface CustomerSoapService {

    /**
     * 根据客户ID获取客户对象
     * @param customerId 客户id
     * @return 客户对象
     */
    Customer getCustomer(long customerId);

}

package com.lin.framework.rest;

import com.lin.framework.annotation.Inject;
import com.lin.framework.annotation.Service;
import com.lin.framework.service.CustomerService;
import com.lin.model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 客户REST服务
 */
@Rest
@Service
@Consumes(MediaType.APPLICATION_JSON) // 输入
@Produces(MediaType.APPLICATION_JSON) // 输出
public class CustomerRestService {

    @Inject
    private CustomerService customerService;

    @GET
    @Path("/customer/{id}")
    public Customer getCustomer(@PathParam("id") long customerId) {
        return customerService.getCustomer(customerId);
    }

}

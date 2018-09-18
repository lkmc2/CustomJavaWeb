package com.lin.framework.soap;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;


import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description SOAP助手类
 */
public final class SoapHelper {

    // 输入拦截器
    private static final List<Interceptor<? extends Message>> inInterceptorList = new ArrayList<>();

    // 输出拦截器
    private static final List<Interceptor<? extends Message>> outInterceptorList = new ArrayList<>();


    static {
        // 添加Logging Intercept
        if (SoapConfig.isLog()) {
            // 将日志输入拦截器加入列表
            LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
            inInterceptorList.add(loggingInInterceptor);

            // 将日志输出拦截器加入列表
            LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
            outInterceptorList.add(loggingOutInterceptor);
        }
    }

    /**
     * 发布SOAP服务
     * @param wsdl WSDL网址
     * @param interfaceClass 接口类
     * @param implementInstance 接口实现类
     */
    public static void publishService(String wsdl,
                                      Class<?> interfaceClass,
                                      Object implementInstance) {
        ServerFactoryBean factory = new ServerFactoryBean();

        // 设置WSDL地址
        factory.setAddress(wsdl);
        // 设置接口类
        factory.setServiceClass(interfaceClass);
        // 设置接口实现类
        factory.setServiceBean(implementInstance);

        // 设置输入拦截器列表
        factory.setInInterceptors(inInterceptorList);
        // 设置输出拦截器列表
        factory.setOutInterceptors(outInterceptorList);
        factory.create();
    }

    /**
     * 创建SOAP客户端
     * @param wsdl WSDL网址
     * @param interfaceClass 接口类
     * @param <T> 接口泛型
     * @return SOAP客户端
     */
    public static <T> T createClient(String wsdl, Class<? extends T> interfaceClass) {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        // 设置WSDL地址
        factory.setAddress(wsdl);
        // 设置接口类
        factory.setServiceClass(interfaceClass);

        // 设置输入拦截器列表
        factory.setInInterceptors(inInterceptorList);
        // 设置输出拦截器列表
        factory.setOutInterceptors(outInterceptorList);
        return factory.create(interfaceClass);
    }

}

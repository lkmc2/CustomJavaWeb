package com.lin.framework.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.lin.framework.helper.BeanHelper;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpInInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPostStreamInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPreStreamInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description REST助手类
 */
public final class RestHelper {

    // 功能提供者列表
    private static final List<Object> providerList = new ArrayList<>();

    // 输入拦截器列表
    private static final List<Interceptor<? extends Message>> inInterceptorList = new ArrayList<>();

    // 输出拦截器列表
    private static final List<Interceptor<? extends Message>> outInterceptorList = new ArrayList<>();

    static {
        // 添加JSON Provider到列表
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        providerList.add(jsonProvider);

        // 添加Logging Interceptor
        if (RestConfig.isLog()) {
            // 将日志输入拦截器加入列表
            LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
            inInterceptorList.add(loggingInInterceptor);

            // 将日志输出拦截器加入列表
            LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
            outInterceptorList.add(loggingOutInterceptor);
        }

        // 添加JSONP Interceptor
        if (RestConfig.isJsonp()) {
            // 将JSONP输入拦截器加入列表
            JsonpInInterceptor jsonpInInterceptor = new JsonpInInterceptor();
            jsonpInInterceptor.setCallbackParam(RestConfig.getJsonpFunction());
            inInterceptorList.add(jsonpInInterceptor);

            // 将JSONP前置流拦截器加入列表
            JsonpPreStreamInterceptor jsonpPreStreamInterceptor = new JsonpPreStreamInterceptor();
            outInterceptorList.add(jsonpPreStreamInterceptor);

            // 将JSONP后置流拦截器加入列表
            JsonpPostStreamInterceptor jsonpPostStreamInterceptor = new JsonpPostStreamInterceptor();
            outInterceptorList.add(jsonpPostStreamInterceptor);
        }

        // 添加CORS Provider
        if (RestConfig.isCors()) {
            CrossOriginResourceSharingFilter crosProvider = new CrossOriginResourceSharingFilter();
            crosProvider.setAllowOrigins(RestConfig.getCorsOriginList());
            providerList.add(crosProvider);
        }
    }

    /**
     * 发布REST服务
     * @param wsdl wsdl网址
     * @param resourceClasses 接口类
     */
    public static void publishService(String wsdl, Class<?> resourceClasses) {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        // 设置网址
        factory.setAddress(wsdl);
        // 设置接口类
        factory.setResourceClasses(resourceClasses);
        // 设置接口功能提供者
        factory.setResourceProvider(resourceClasses, new SingletonResourceProvider(BeanHelper.getBean(resourceClasses)));
        // 设置功能提供者列表
        factory.setProviders(providerList);

        // 设置输入拦截器列表
        factory.setInInterceptors(inInterceptorList);
        // 设置输出拦截器列表
        factory.setOutInterceptors(outInterceptorList);
        factory.create();
    }

    /**
     * 创建REST客户端
     * @param wsdl wsdl网址
     * @param resourceClass 接口类
     * @param <T> 接口类型
     * @return REST客户端
     */
    public static <T> T createClient(String wsdl, Class<? extends T> resourceClass) {
        return JAXRSClientFactory.create(wsdl, resourceClass, providerList);
    }

}

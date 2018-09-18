package com.lin.framework.rest;

import com.lin.framework.helper.BeanHelper;
import com.lin.framework.helper.ClassHelper;
import com.lin.framework.soap.Soap;
import com.lin.framework.soap.SoapHelper;
import com.lin.utils.CollectionUtil;
import com.lin.utils.StringUtil;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description REST Servlet
 */
@WebServlet(urlPatterns = RestConstant.SERVLET_URL, loadOnStartup = 0)
public class RestServlet extends CXFNonSpringServlet {

    @Override
    protected void loadBus(ServletConfig sc) {
        // 初始化CXF总线
        super.loadBus(sc);
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);

        // 发布REST服务
        publishRestService();
    }

    /**
     * 发布REST服务
     */
    private void publishRestService() {
        // 遍历所有带有REST注解的类
        Set<Class<?>> restClassSet = ClassHelper.getClassSetByAnnotation(Rest.class);

        if (CollectionUtil.isNotEmpty(restClassSet)) {
            for (Class<?> restClass : restClassSet) {
                // 获取REST地址
                String address = getAddress(restClass);

                // 发布REST服务
                RestHelper.publishService(address, restClass);
            }
        }
    }

    /**
     * 根据带REST注解的类REST地址
     * @param restClass SOAP注解
     * @return SOAP地址
     */
    private String getAddress(Class<?> restClass) {
        String address;

        //  若SOAP注解的value属性不为空，则获取当前值，否则获取类名
        String soapValue = restClass.getAnnotation(Rest.class).value();

        if (StringUtil.isNotEmpty(soapValue)) {
            address = soapValue;
        } else {
            address = restClass.getSimpleName();
        }

        //  确保最前面只有一个 / 符号
        if (!address.startsWith("/")) {
            address = "/" + address;
        }

        address = address.replaceAll("\\/+", "/");

       return address;
    }

}

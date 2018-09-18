package com.lin.framework.soap;

import com.lin.framework.helper.BeanHelper;
import com.lin.framework.helper.ClassHelper;
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
 * @description SOAP Servlet
 */
@WebServlet(urlPatterns = SoapConstant.SERVLET_URL, loadOnStartup = 0)
public class SoapServlet extends CXFNonSpringServlet {

    @Override
    protected void loadBus(ServletConfig sc) {
        // 初始化CXF总线
        super.loadBus(sc);
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);

        // 发布SOAP服务
        publishSoapService();
    }

    /**
     * 发布SOAP服务
     */
    private void publishSoapService() {
        // 遍历所有带有SOAP注解的类
        Set<Class<?>> soapClassSet = ClassHelper.getClassSetByAnnotation(Soap.class);

        if (CollectionUtil.isNotEmpty(soapClassSet)) {
            for (Class<?> soapClass : soapClassSet) {
                // 获取SOAP地址
                String address = getAddress(soapClass);

                // 获取SOAP类的接口
                Class<?> soapInterfaceClass = getSoapInterfaceClass(soapClass);

                // 获取SOAP类的实例
                Object soapInstance = BeanHelper.getBean(soapClass);

                // 发布SOAP服务
                SoapHelper.publishService(address, soapInterfaceClass, soapInstance);
            }
        }
    }

    /**
     * 获取SOAP实现类的第一个接口作为SOAP服务接口
     */
    private Class<?> getSoapInterfaceClass(Class<?> soapClass) {
        return soapClass.getInterfaces()[0];
    }

    /**
     * 根据SOAP注解获取SOAP地址
     * @param soapClass SOAP注解
     * @return SOAP地址
     */
    private String getAddress(Class<?> soapClass) {
        String address;

        //  若SOAP注解的value属性不为空，则获取当前值，否则获取类名
        String soapValue = soapClass.getAnnotation(Soap.class).value();

        if (StringUtil.isNotEmpty(soapValue)) {
            address = soapValue;
        } else {
            // 获取SOAP实现类的第一个接口名
            address = getSoapInterfaceClass(soapClass).getSimpleName();
        }

        //  确保最前面只有一个 / 符号
        if (!address.startsWith("/")) {
            address = "/" + address;
        }

        address = address.replaceAll("\\/+", "/");

       return address;
    }

}

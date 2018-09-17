package com.lin.framework.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 自定义Smart Security插件
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> handlesTypes, ServletContext servletContext) throws ServletException {
        // 设置初始化参数（shiro配置文件的路径）
        servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");

        // 注册 Listener
        servletContext.addListener(EnvironmentLoaderListener.class);

        // 注册 Filter
        FilterRegistration.Dynamic smartSecurityFilter =
                servletContext.addFilter("SmartSecurityFilter", SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }

}

package com.lin.framework;

import com.lin.framework.bean.Data;
import com.lin.framework.bean.Handler;
import com.lin.framework.bean.Param;
import com.lin.framework.bean.View;
import com.lin.framework.helper.BeanHelper;
import com.lin.framework.helper.ConfigHelper;
import com.lin.framework.helper.ControllerHelper;
import com.lin.framework.util.ArrayUtil;
import com.lin.framework.util.ReflectionUtil;
import com.lin.framework.util.CodecUtil;
import com.lin.framework.util.JsonUtil;
import com.lin.framework.util.StreamUtil;
import com.lin.utils.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 请求转发器
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关Helper类
        HelperLoader.init();

        // 获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = config.getServletContext();

        // 注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        // 注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求方法和请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        // 获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

        if (handler != null) {
            // 获取Controller类及其Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            // 创建请求参数对象
            HashMap<String, Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = request.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }

            // 获取请求体
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));

            if (StringUtil.isNotEmpty(body)) {
                // 切割字符串
                String[] params = StringUtil.splitString(body, "&");

                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        // 切割字符串
                        String[] array = StringUtil.splitString(param, "=");

                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];

                            // 将参数名和参数值放入请求参数对象中
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }

            // 生成请求参数对象
            Param param = new Param(paramMap);

            // 调用请求方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);

            // 处理Action方法的返回值
            if (result instanceof View) {
                // 返回JSP页面
                View view = (View) result;
                String path = view.getPath();

                if (StringUtil.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();

                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }

                        request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                                .forward(request, response);
                    }
                }
            } else if (result instanceof Data) {
                // 返回JSON数据
                Data data = (Data) result;
                Object model = data.getModel();

                if (model != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);

                    assert json != null;

                    // 写入json数据给前端
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}

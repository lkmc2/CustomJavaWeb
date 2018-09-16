package com.lin.framework;

import com.lin.framework.bean.Data;
import com.lin.framework.bean.Handler;
import com.lin.framework.bean.Param;
import com.lin.framework.bean.View;
import com.lin.framework.helper.*;
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

        // 初始化文件上传助手类
        UploadHelper.init(servletContext);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初始化Servlet助手类
        ServletHelper.init(request, response);

        try {
            // 获取请求方法和请求路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();

            // 请求网站图标，结束方法
            if (requestPath.equals("/favicon.ico")) {
                return;
            }

            // 获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

            if (handler != null) {
                // 获取Controller类及其Bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                // 请求参数对象
                Param param;

                if (UploadHelper.isMultipart(request)) {
                    // 请求为 Multipart 类型，用文件上传助手创建参数
                    param = UploadHelper.createParam(request);
                } else {
                    // 请求不为 Multipart 类型，使用请求助手创建参数
                    param = RequestHelper.createParam(request);
                }

                // 调用请求方法
                Method actionMethod = handler.getActionMethod();

                // 方法执行结果
                Object result;

                // 请求参数为空时，调用方法是不传入参数
                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }

                // 处理Action方法的返回值
                if (result instanceof View) {
                    // 返回JSP页面
                    handleViewResult((View) result, request, response);
                } else if (result instanceof Data) {
                    // 返回JSON数据
                    handelDataResult((Data) result, response);
                }
            }
        } finally {
            // 销毁Servlet助手类
            ServletHelper.destroy();
        }
    }

    /**
     * 返回JSP页面
     * @param view 返回视图对象
     * @param request 请求
     * @param response 响应
     */
    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 请求路径
        String path = view.getPath();

        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                // 获取数据模型
                Map<String, Object> model = view.getModel();

                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }

                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                        .forward(request, response);
            }
        }
    }

    /**
     * 返回JSON数据
     * @param data 返回数据对象
     * @param response 响应
     */
    private void handelDataResult(Data data, HttpServletResponse response) throws IOException {
        // 返回的对象模型
        Object model = data.getModel();

        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 将对象模型转换成json字符串
            String json = JsonUtil.toJson(model);

            assert json != null;

            // 写入json数据给前端
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

}

package com.lin.framework.helper;

import com.lin.framework.annotation.Action;
import com.lin.framework.bean.Handler;
import com.lin.framework.bean.Request;
import com.lin.framework.util.ArrayUtil;
import com.lin.utils.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 控制器助手类
 */
public final class ControllerHelper {

    // Action Map（用于存放请求与处理器的映射关系）
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();

                if (ArrayUtil.isNotEmpty(methods)) {
                    // 遍历这些Controller类值的方法
                    for (Method method : methods) {
                        // 判断当前方法是否带有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            // 验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")) { // 例如：never:/show
                                String[] array = mapping.split(":");

                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];

                                    // 生成封装请求信息的对象
                                    Request request = new Request(requestMethod, requestPath);
                                    // 生成封装Action信息的对象
                                    Handler handler = new Handler(controllerClass, method);

                                    // 存放请求与处理器的映射关系到Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requestMethod 请求方法
     * @param requestPath 请求路径
     * @return Handler对象
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}

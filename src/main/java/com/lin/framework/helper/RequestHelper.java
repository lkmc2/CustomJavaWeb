package com.lin.framework.helper;

import com.lin.framework.bean.FormParam;
import com.lin.framework.bean.Param;
import com.lin.framework.util.ArrayUtil;
import com.lin.framework.util.CodecUtil;
import com.lin.framework.util.StreamUtil;
import com.lin.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 请求助手类
 */
public final class RequestHelper {

    /**
     * 创建请求对象
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        // 表单参数列表
        List<FormParam> formParamsList = new ArrayList<>();

        // 解析参数
        formParamsList.addAll(parseParameterNames(request));
        // 解析输入流
        formParamsList.addAll(parseInputStream(request));

        return new Param(formParamsList);
    }

    /**
     * 解析参数
     */
    private static List<FormParam> parseParameterNames(HttpServletRequest request) {
        // 表单参数列表
        List<FormParam> formParamsList = new ArrayList<>();

        // 获取请求中的所有参数名枚举
        Enumeration<String> paramNames = request.getParameterNames();

        // 遍历参数名枚举
        while (paramNames.hasMoreElements()) {
            // 获取参数名
            String fieldName = paramNames.nextElement();
            // 通过参数名获取参数值列表
            String[] fieldValues = request.getParameterValues(fieldName);

            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;

                if (fieldValues.length == 1) {
                    // 参数值列表只有一个参数值，获取该参数值
                    fieldValue = fieldValues[0];
                } else {
                    // 参数值列表有多个参数值，将参数值进行拼接，用符号分隔
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < fieldValues.length; i++) {
                        sb.append(fieldValues[0]);
                        // 非最后一个参数值时，添加分隔符号
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();
                }
                // 将表单数据添加到列表
                formParamsList.add(new FormParam(fieldName, fieldValue));
            }
        }

        return formParamsList;
    }

    /**
     * 解析输入流
     */
    private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        // 表单参数列表
        List<FormParam> formParamsList = new ArrayList<>();

        // 从请求的输入流中获取请求体
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));

        if (StringUtil.isNotEmpty(body)) {
            // 将请求体拆分成键值对数组
            String[] kvs = StringUtil.splitString(body, "&");

            // 遍历键值对数组
            for (String kv : kvs) {
                // 拆分键值对
                String[] array = StringUtil.splitString(kv, "=");
                String fieldName = array[0];
                String fieldValue = array[1];

                // 添加表单参数到列表
                formParamsList.add(new FormParam(fieldName, fieldValue));
            }
        }

        return formParamsList;
    }

}

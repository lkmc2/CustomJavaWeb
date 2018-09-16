package com.lin.framework.bean;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 封装表单参数
 */
public class FormParam {

    private String fieldName; // 参数名
    private Object fieldValue; // 参数值

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}

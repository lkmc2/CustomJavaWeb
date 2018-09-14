package com.lin.framework.bean;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 返回数据对象
 */
public class Data {

    // 模型数据
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }

}

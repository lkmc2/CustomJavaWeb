package com.lin.framework.bean;

import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 返回视图对象
 */
public class View {

    // 视图路径
    private String path;

    // 模型数据
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
    }

    /**
     * 添加模型到视图对象中
     */
    private View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

}

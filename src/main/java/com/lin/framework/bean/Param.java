package com.lin.framework.bean;

import com.lin.utils.CastUtil;
import com.lin.utils.CollectionUtil;

import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 请求参数对象
 */
public class Param {

    // 请求参数
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     * @param name 参数名
     * @return 参数名对应的long型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(paramMap);
    }

}

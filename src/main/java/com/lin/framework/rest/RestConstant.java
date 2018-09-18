package com.lin.framework.rest;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description REST插件常量
 */
public interface RestConstant {

    // 请求网址路径
    String SERVLET_URL = "/rest/*";

    // 是否开启REST日志记录
    String LOG = "smart.plugin.rest.log";

    // 是否开启JSONP
    String JSONP = "smart.plugin.rest.jsonp";

    // JSONP函数
    String JSONP_FUNCTION = "smart.plugin.rest.jsonp.function";

    // 是否开启CORS
    String CORS = "smart.plugin.rest.cors";

    // CORS orgin列表
    String CORS_ORIGIN = "smart.plugin.rest.cors.origin";

}

package demo.cxf.client;

import demo.cxf.HelloService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 基于CXF simple方法的客户端（只能访问simple服务器）
 */
public class SimpleClient {

    public static void main(String[] args) {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();

        // 设置请求服务地址
        factory.setAddress("http://localhost:8080/ws/soap/hello");

        // 设置访问接口
        factory.setServiceClass(HelloService.class);

        // 代理工厂创建接口
        HelloService helloService = factory.create(HelloService.class);

        // 调用接口方法
        String result = helloService.say("world");

        System.out.println(result);

        /*
            运行结果：
            hello world
         */
    }

}

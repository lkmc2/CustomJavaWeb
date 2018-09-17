package demo.cxf.client;

import demo.cxf.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description Jax静态代理客户端
 */
public class JaxWsClient {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

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

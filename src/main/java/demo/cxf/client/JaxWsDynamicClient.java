package demo.cxf.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description Jax动态代理客户端
 */
public class JaxWsDynamicClient {

    public static void main(String[] args) {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();

        // 创建客户端
        Client client = factory.createClient("http://localhost:8080/ws/soap/hello?wsdl");

        try {
            // 调用服务器接口的say方法，传入参数world
            Object[] results = client.invoke("say", "world");

            System.out.println(results[0]);

            // 运行结果：hello world
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

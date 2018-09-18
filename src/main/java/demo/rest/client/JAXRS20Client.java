package demo.rest.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description JAX-RS 2.0时代的客户端
 */
public class JAXRS20Client {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        // JSON解析器
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

        // 创建客户端，并获取所有产品列表
        List list = ClientBuilder.newClient()
                .register(jsonProvider)
                .target(baseAddress)
                .path("/products")
                .request(MediaType.APPLICATION_JSON)
                .get(List.class);

        // 遍历输出产品列表
        list.forEach(System.out::println);

        /*
            运行前需启动 demo.rest.server.RestServer 服务器
            运行结果：
            {id=1, name=Apple, price=3.0}
            {id=2, name=Orange, price=4.0}
            {id=3, name=Banana, price=2.0}
            {id=4, name=Pear, price=6.0}
         */
    }

}

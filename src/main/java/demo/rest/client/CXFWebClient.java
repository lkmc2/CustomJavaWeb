package demo.rest.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import demo.rest.entity.Product;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 通用WebClient客户端
 */
public class CXFWebClient {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        // 添加Json解析器
        List<Object> providerList = new ArrayList<>();
        providerList.add(new JacksonJsonProvider());

        // 创建客户端连接并获取产品列表
        List<Product> productList = WebClient.create(baseAddress, providerList)
                .path("/products")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>(){ });

        // 遍历产品列表
        productList.forEach(System.out::println);

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

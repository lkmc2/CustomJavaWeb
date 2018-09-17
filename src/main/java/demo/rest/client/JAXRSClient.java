package demo.rest.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import demo.rest.entity.Product;
import demo.rest.service.ProductService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description JAX-RS 1.0时代的客户端
 */
public class JAXRSClient {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        // 设置REST服务所需的Provider
        List<Object> providerList = new ArrayList<>();
        providerList.add(new JacksonJsonProvider());

        // 创建产品服务
        ProductService productService = JAXRSClientFactory.
                create(baseAddress, ProductService.class, providerList);

        // 获取所有产品
        List<Product> products = productService.retrieveAllProducts();

        for (Product product : products) {
            System.out.println(product);
        }

        /*
            运行需启动 demo.rest.server.RestServer 服务器
            运行结果：
            Product{id=1, name='Apple', price=3.0}
            Product{id=2, name='Orange', price=4.0}
            Product{id=3, name='Banana', price=2.0}
            Product{id=4, name='Pear', price=6.0}
         */
    }

}

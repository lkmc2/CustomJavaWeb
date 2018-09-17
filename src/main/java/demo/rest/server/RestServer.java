package demo.rest.server;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import demo.rest.service.impl.ProductServiceImpl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description REST服务器
 */
public class RestServer {

    public static void main(String[] args) {
        // 添加ResourceClass
        List<Class<?>> resourceClassList = new ArrayList<>();
        // 添加产品服务实现类到列表
        resourceClassList.add(ProductServiceImpl.class);

        // 添加ResourceProvider
        List<ResourceProvider> resourceProviderList = new ArrayList<>();
        resourceProviderList.add(new SingletonResourceProvider(new ProductServiceImpl()));

        // 添加Provider
        List<Object> productList = new ArrayList<>();
        productList.add(new JacksonJsonProvider());

        // 发布REST服务
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/rest");

        // 设置对应接口实现类
        factory.setResourceClasses(resourceClassList);

        // 设置资源类对应的Provider
        factory.setResourceProviders(resourceProviderList);

        // 设置REST服务所需的Provider
        factory.setProviders(productList);

        factory.create();

        System.out.println("rest ws is published");
    }

}

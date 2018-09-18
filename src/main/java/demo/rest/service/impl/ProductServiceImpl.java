package demo.rest.service.impl;

import demo.rest.entity.Product;
import demo.rest.service.ProductService;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 产品服务实现类
 */
@Component
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> retrieveAllProducts() {
        return new ArrayList<Product>() {{
            add(new Product(1, "Apple", 3));
            add(new Product(2, "Orange", 4));
            add(new Product(3, "Banana", 2));
            add(new Product(4, "Pear", 6));
        }};
    }

    @Override
    public Product retrieveProductsById(long id) {
        return null;
    }

    @Override
    public List<Product> retrieveProductsByName(String name) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> updateProductById(long id, Map<String, Object> fieldMap) {
        return null;
    }

    @Override
    public Product deleteProductById(long id) {
        return null;
    }

}

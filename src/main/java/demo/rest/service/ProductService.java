package demo.rest.service;

import demo.rest.entity.Product;

import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 产品服务接口
 */
public interface ProductService {

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> retrieveAllProducts(); // 获取所有产品

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product retrieveProductsById(@PathParam("id") long id); // 获取单个产品

    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // 输入
    @Produces(MediaType.APPLICATION_JSON) // 输出
    List<Product> retrieveProductsByName(@FormParam("name") String name); // 获取指定名字的产品列表

    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Product createProduct(Product product); // 创建产品

    @PUT
    @Path("/product/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> updateProductById(@PathParam("id") long id, Map<String, Object> fieldMap); // 更新产品

    @DELETE
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product deleteProductById(@PathParam("id") long id); // 删除产品

}

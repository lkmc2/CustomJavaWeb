package demo.threadlocal.dbutil;

import demo.threadlocal.dbutil.service.ProductService;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 客户端线程
 */
public class ClientThread extends Thread {

    // 产品服务
    private ProductService productService;

    public ClientThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

        // 更新指定id的产品价格
        productService.updateProductPrice(1, 3000);
    }
}

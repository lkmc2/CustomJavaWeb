package demo.threadlocal.dbutil;

import demo.threadlocal.dbutil.service.impl.ProductServiceImpl;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 主测试类（单线程执行）
 */
public class TestMain {

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.updateProductPrice(1, 3000);

        /*
            运行结果：
            更新产品成功！
            插入日志成功！
         */
    }

}

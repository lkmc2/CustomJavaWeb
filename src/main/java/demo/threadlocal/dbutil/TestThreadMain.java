package demo.threadlocal.dbutil;

import demo.threadlocal.dbutil.service.impl.ProductServiceImpl;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 主测试类（多线程执行）
 */
public class TestThreadMain {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // 产品服务实现类
            ProductServiceImpl productService = new ProductServiceImpl();

            // 客户端线程
            ClientThread thread = new ClientThread(productService);
            thread.start();
        }
        /*
            运行结果：（多线程运行正常）
            Thread-0
            Thread-1
            Thread-2
            Thread-3
            Thread-4
            Thread-5
            Thread-6
            Thread-7
            Thread-8
            Thread-9
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
         */
    }

}

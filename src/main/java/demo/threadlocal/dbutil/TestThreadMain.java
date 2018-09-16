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
            运行结果：（多线程运行时出现连接被关闭的异常）
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
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
            更新产品成功！
            插入日志成功！
com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: No operations allowed after connection closed.
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:425)
	at com.mysql.jdbc.Util.getInstance(Util.java:408)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:919)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:898)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:887)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:861)
	at com.mysql.jdbc.ConnectionImpl.throwConnectionClosedException(ConnectionImpl.java:1184)
	at com.mysql.jdbc.ConnectionImpl.checkClosed(ConnectionImpl.java:1179)
	at com.mysql.jdbc.ConnectionImpl.prepareStatement(ConnectionImpl.java:4056)
	at com.mysql.jdbc.ConnectionImpl.prepareStatement(ConnectionImpl.java:4025)
	at demo.threadlocal.dbutil.service.impl.ProductServiceImpl.insertLog(ProductServiceImpl.java:77)
	at demo.threadlocal.dbutil.service.impl.ProductServiceImpl.updateProductPrice(ProductServiceImpl.java:36)
	at demo.threadlocal.dbutil.ClientThread.run(ClientThread.java:24)
         */
    }

}

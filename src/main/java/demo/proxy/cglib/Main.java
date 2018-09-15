package demo.proxy.cglib;

import demo.proxy.hello.Hello;
import demo.proxy.hello.impl.HelloImpl;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class Main {

    public static void main(String[] args) {
        // CGLib动态代理
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");

        /*
            运行结果：
            Before
            Hello!Jack
            After
         */
    }

}

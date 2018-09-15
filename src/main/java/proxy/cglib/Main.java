package proxy.cglib;

import proxy.hello.Hello;
import proxy.hello.impl.HelloImpl;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class Main {

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();

        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("Jack");

        /*
            运行结果：
            Before
            Hello!Jack
            After
         */
    }

}

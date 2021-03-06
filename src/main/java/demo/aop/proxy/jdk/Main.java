package demo.aop.proxy.jdk;

import demo.aop.proxy.hello.Hello;
import demo.aop.proxy.hello.impl.HelloImpl;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class Main {

    public static void main(String[] args) {
        // JDK动态代理类
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());

        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("Jack");

        /*
            运行结果：
            Before
            Hello!Jack
            After
         */
    }

}

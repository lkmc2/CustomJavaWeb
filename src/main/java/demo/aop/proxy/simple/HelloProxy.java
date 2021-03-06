package demo.aop.proxy.simple;

import demo.aop.proxy.hello.Hello;
import demo.aop.proxy.hello.impl.HelloImpl;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 打招呼代理类（静态代理）
 */
public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy() {
        hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

}

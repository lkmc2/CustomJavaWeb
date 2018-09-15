package demo.aspect;

import demo.aspect.greeting.Greeting;
import demo.aspect.greeting.impl.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 客户端
 */
public class Client {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); // 创建代理工厂
        proxyFactory.setTarget(new GreetingImpl()); // 设置目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); // 添加前置增加
        proxyFactory.addAdvice(new GreetingAfterAdvice()); // 添加后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHi("Andy");

        /*
            运行结果：
            Before
            Hi! Andy
            After
         */

        ProxyFactory aroundProxyFactory = new ProxyFactory(); // 创建代理工厂
        aroundProxyFactory.setTarget(new GreetingImpl()); // 设置目标类对象
        aroundProxyFactory.addAdvice(new GreetingAroundAdvice()); // 添加环绕增强

        Greeting aroundGreeting = (Greeting) aroundProxyFactory.getProxy();
        aroundGreeting.sayHi("Luck");

        /*
            运行结果：
            Before
            Hi! Luck
            After
         */
    }

}

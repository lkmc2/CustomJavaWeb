package demo.aop.weaving;


import demo.aop.weaving.apology.Apology;
import demo.aop.weaving.greeting.impl.TalkingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 客户端
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo/aop/weaving/weaving.xml");

        // 转换为目标类，并非它的Greeting接口
        TalkingImpl greetingImpl = (TalkingImpl) context.getBean("greetingProxy");
        greetingImpl.sayHi("Tian");

        // 将目标强制转换成Apology接口（接口动态实现，见weaving.xml的proxyTargetClass和interceptorNames）
        Apology apology = (Apology) greetingImpl;
        apology.saySorry("Tian");

        /*
            运行结果：
            Hi! Tian
            Sorry! Tian
         */
    }

}

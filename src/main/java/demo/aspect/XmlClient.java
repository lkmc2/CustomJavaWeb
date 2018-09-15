package demo.aspect;

import demo.aspect.greeting.Greeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 基于xml配置的客户端
 */
public class XmlClient {

    public static void main(String[] args) {
        // 加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("demo/aspect/aop.xml");

        // 从context中根据id获取Bean对象（其实就是一个代理）
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        greeting.sayHi("Wang");

        /*
            运行结果：
            Before
            Hi! Wang
            After
         */
    }

}

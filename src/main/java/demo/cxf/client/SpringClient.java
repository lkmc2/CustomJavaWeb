package demo.cxf.client;

import demo.cxf.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 基于Spring的客户端
 */
public class SpringClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-cxf.xml");

        // 从Spring中获取接口
        HelloService helloService = context.getBean("helloService", HelloService.class);

        // 调用接口
        String result = helloService.say("world");

        System.out.println(result);

        // 运行结果：hello world
    }

}

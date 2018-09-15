package demo.aop.springaspect;


import demo.aop.springaspect.entity.run.Running;
import demo.aop.springaspect.entity.smile.impl.SmileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 引入增强客户端
 */
public class AdvanceClient {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo/aop/springaspect/springaspect.xml");

        SmileImpl smileImpl = (SmileImpl) context.getBean("smileImpl");
        smileImpl.smile("Jack");

        Running runningImpl = (Running) smileImpl;
        runningImpl.running("Funny");

        // 这里使用SmileAspect类，动态给未实现Running接口的smileImpl对象添加并实现该接口

        /*
            运行结果：
            I am running! Jack
            I am running! Funny
         */
    }

}

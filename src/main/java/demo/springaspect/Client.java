package demo.springaspect;


import demo.advisor.meeting.impl.MeetingImpl;
import demo.springaspect.flying.impl.FlyingImpl;
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
                = new ClassPathXmlApplicationContext("demo/springaspect/springaspect.xml");

        FlyingImpl flyingImpl = (FlyingImpl) context.getBean("flyingImpl");
        flyingImpl.flying("Wang");

        /*
            运行结果：
            Before
            I am flying! Wang
            After
         */
    }

}

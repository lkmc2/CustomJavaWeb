package demo.advisor;


import demo.advisor.meeting.impl.MeetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 自动代理客户端
 */
public class AutoProxyClient {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo/advisor/auto_proxy.xml");

        MeetingImpl meetingImpl = (MeetingImpl) context.getBean("meetingImpl");
        meetingImpl.sayHi("Jack");
        meetingImpl.goodMorning("Andy");
        meetingImpl.goodNight("Wang");

        // 将对MeetingImpl类中以good开头的方法进行拦截，并处理

        /*
            运行结果：
            Hi! Jack
            ------------------
            Before goodMorning
            Good Morning! Andy
            After goodMorning
            ------------------
            Before goodNight
            Good Night! Wang
            After goodNight
         */
    }

}

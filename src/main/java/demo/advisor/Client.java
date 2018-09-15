package demo.advisor;


import demo.advisor.meeting.impl.MeetingImpl;
import demo.weaving.apology.Apology;
import demo.weaving.greeting.impl.TalkingImpl;
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
                = new ClassPathXmlApplicationContext("demo/advisor/advisor.xml");

        MeetingImpl meetingImpl = (MeetingImpl) context.getBean("meetingProxy");
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

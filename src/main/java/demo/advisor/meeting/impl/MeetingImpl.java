package demo.advisor.meeting.impl;

import demo.advisor.meeting.Meeting;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 见面实现类
 */
@Component
public class MeetingImpl implements Meeting {

    @Override
    public void sayHi(String name) {
        System.out.println("Hi! " + name);
    }

    public void goodMorning(String name) {
        System.out.println("Good Morning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night! " + name);
    }

}

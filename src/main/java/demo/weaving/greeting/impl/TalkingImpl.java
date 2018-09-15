package demo.weaving.greeting.impl;

import demo.weaving.greeting.Talking;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 聊天实现类
 */
@Component
public class TalkingImpl implements Talking {

    @Override
    public void sayHi(String name) {
        System.out.println("Hi! " + name);
    }

}

package demo.aspect.greeting.impl;

import demo.aspect.greeting.Greeting;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 打招呼实现类
 */
@Component
public class GreetingImpl implements Greeting {

    @Override
    public void sayHi(String name) {
        System.out.println("Hi! " + name);
    }

}

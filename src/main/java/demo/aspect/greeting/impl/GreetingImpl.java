package demo.aspect.greeting.impl;

import demo.aspect.greeting.Greeting;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 打招呼实现类
 */
public class GreetingImpl implements Greeting {

    @Override
    public void sayHi(String name) {
        System.out.println("Hi! " + name);
    }

}

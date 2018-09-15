package proxy.impl;

import proxy.Hello;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 打招呼实现类
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello!" + name);
    }
}

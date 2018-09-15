package demo.springaspect.flying.impl;

import demo.springaspect.flying.Flying;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 飞行实现类
 */
@Component
public class FlyingImpl implements Flying {

    @Override
    public void flying(String name) {
        System.out.println("I am flying! " + name);
    }

}

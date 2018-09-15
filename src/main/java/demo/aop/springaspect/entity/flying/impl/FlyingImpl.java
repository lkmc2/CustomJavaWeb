package demo.aop.springaspect.entity.flying.impl;

import demo.aop.springaspect.entity.flying.Flying;
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
        System.out.println("I am running! " + name);
    }

}

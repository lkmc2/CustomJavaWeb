package demo.springaspect.entity.smile.impl;

import demo.springaspect.entity.smile.Smile;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 微笑实现类
 */
@Component
public class SmileImpl implements Smile {

    @Override
    public void smile(String name) {
        System.out.println("I am running! " + name);
    }

}

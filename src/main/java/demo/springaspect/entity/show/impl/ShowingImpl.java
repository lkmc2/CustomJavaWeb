package demo.springaspect.entity.show.impl;

import demo.springaspect.annotation.Tag;
import demo.springaspect.entity.show.Showing;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 表演实现类
 */
@Component
public class ShowingImpl implements Showing {

    @Override
    public void showing(String name) {
        System.out.println("I am showing! " + name);
    }

    @Tag
    public void laughing(String name) {
        System.out.println("Let's start laugh! " + name);
    }

}

package demo.springaspect.entity.run;

import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 跑步实现类
 */
@Component
public class RunningImpl implements Running {

    @Override
    public void running(String name) {
        System.out.println("I am running! " + name);
    }

}

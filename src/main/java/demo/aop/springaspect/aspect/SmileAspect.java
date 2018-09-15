package demo.aop.springaspect.aspect;

import demo.aop.springaspect.entity.run.Running;
import demo.aop.springaspect.entity.run.RunningImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 微笑切面（Spring + AspectJ，引入增强）
 */
@Aspect
@Component
public class SmileAspect {

    /*
        使用引入增强（给一个对象动态添加未实现的接口中的方法）
        value 目标类
        defaultImpl 引入接口的默认实现类
     */
    @DeclareParents(value = "demo.aop.springaspect.entity.smile.impl.SmileImpl", defaultImpl = RunningImpl.class)
    private Running running;

}

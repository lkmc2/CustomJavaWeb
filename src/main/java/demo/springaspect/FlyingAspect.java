package demo.springaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 飞行切面（Spring + AspectJ，基于注解：通过AspectJ execution表达式拦截方法）
 */
@Aspect
@Component
public class FlyingAspect {

    /*
     * execution表达式
     * 第一个 * 表示返回值
     * 第二个 * 表示方法名
     * (..) 表示方法参数，任何参数都可以
     */
    @Around("execution(* demo.springaspect.flying.impl.FlyingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

}

package demo.springaspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 表演标签切面（Spring + AspectJ，基于注解：通过AspectJ @annotation表达式拦截方法）
 */
@Aspect
@Component
public class ShowingTagAspect {

    // 拦截带有@Tag注解的方法
    @Around("@annotation(demo.springaspect.annotation.Tag)")
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

package demo.aspect;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 抛出增强类（异常处理）
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("------- Throw Exception -------");
        System.out.println("Target Class:" + target.getClass().getName());
        System.out.println("Method Name:" + method.getName());
        System.out.println("Exception Message:" + e.getMessage());
        System.out.println("-------------------------------");
    }

}

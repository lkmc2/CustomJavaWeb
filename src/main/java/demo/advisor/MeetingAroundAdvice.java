package demo.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 见面环绕增强类
 */
@Component
public class MeetingAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();

        printLine();

        before(methodName);
        Object result = invocation.proceed();
        after(methodName);

        return result;
    }

    /**
     * 打印分隔线
     */
    private void printLine() {
        System.out.println("------------------");
    }

    private void before(String methodName) {
        System.out.println("Before " + methodName);
    }

    private void after(String methodName) {
        System.out.println("After " + methodName);
    }

}

package demo.weaving;

import demo.weaving.apology.Apology;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 聊天增强类（用于给TalkingImpl类动态添加Apology接口）
 */
@Component
public class TalkingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }

}

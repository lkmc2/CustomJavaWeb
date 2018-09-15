package demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description CGLib代理类
 */
public class CGLibProxy implements MethodInterceptor {

    // 单例实例
    private static final CGLibProxy INSTANCE = new CGLibProxy();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

    /**
     * 获取代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<?> cls) {
        return (T) Enhancer.create(cls, this);
    }

    /**
     * 获取动态代理类的实例
     */
    public static CGLibProxy getInstance() {
        return INSTANCE;
    }

}

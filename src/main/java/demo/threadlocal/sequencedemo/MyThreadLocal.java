package demo.threadlocal.sequencedemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 自定义ThreadLocal
 */
public class MyThreadLocal<T> {

    // 带synchronized锁的Map
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<>());

    /**
     * 设置变量值
     */
    public void set(T value) {
        // 往当前线程中存入值
        container.put(Thread.currentThread(), value);
    }

    /**
     * 获取变量值
     */
    public T get() {
        Thread thread = Thread.currentThread();
        // 获取当前线程对应的值
        T value = container.get(thread);

        // 值为空并且Map中不包含该线程的key
        if (value == null && !container.containsKey(thread)) {
            // 设置该线程对应的初始值并存入Map中
            value = initialValue();
            container.put(thread, value);
        }

        return value;
    }

    /**
     * 设置初始值
     */
    protected T initialValue() {
        return null;
    }

}

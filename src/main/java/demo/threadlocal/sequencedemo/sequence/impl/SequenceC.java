package demo.threadlocal.sequencedemo.sequence.impl;

import demo.threadlocal.sequencedemo.MyThreadLocal;
import demo.threadlocal.sequencedemo.sequence.Sequence;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 序列C（使用自定义ThreadLocal保证线程安全）
 */
public class SequenceC implements Sequence {

    // 使用自定义ThreadLocal保证线程安全
    private static MyThreadLocal<Integer> number = new MyThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        number.set(number.get() + 1);
        return number.get();
    }

}

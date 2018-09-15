package demo.threadlocal.sequencedemo.sequence.impl;

import demo.threadlocal.sequencedemo.sequence.Sequence;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 序列B（使用jdk自带的ThreadLocal保证线程安全）
 */
public class SequenceB implements Sequence {

    // 使用ThreadLocal保证线程安全
    private static ThreadLocal<Integer> number = ThreadLocal.withInitial(() -> 0);

    @Override
    public int getNumber() {
        number.set(number.get() + 1);
        return number.get();
    }

}

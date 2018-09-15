package demo.threadlocal.sequence.impl;

import demo.threadlocal.sequence.Sequence;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 序列A
 */
public class SequenceA implements Sequence {

    // static无法保证线程安全
    private static int number = 0;

    @Override
    public int getNumber() {
        number = number + 1;
        return number ;
    }

}

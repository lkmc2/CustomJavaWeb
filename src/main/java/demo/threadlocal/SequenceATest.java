package demo.threadlocal;

import demo.threadlocal.sequence.Sequence;
import demo.threadlocal.sequence.impl.SequenceA;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class SequenceATest {

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();

        /*
            运行结果：（线程运行次序是随机的，并且所有线程共用static变量）
            Thread-0 => 1
            Thread-1 => 2
            Thread-1 => 3
            Thread-1 => 4
            Thread-0 => 5
            Thread-0 => 6
            Thread-2 => 7
            Thread-2 => 8
            Thread-2 => 9
         */
    }

}

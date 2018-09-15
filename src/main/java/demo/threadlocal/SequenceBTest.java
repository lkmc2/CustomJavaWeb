package demo.threadlocal;

import demo.threadlocal.sequence.Sequence;
import demo.threadlocal.sequence.impl.SequenceB;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class SequenceBTest {

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();

        /*
            运行结果：（线程运行次序是随机的，但是每个线程单独拥有自己的static变量）
            Thread-0 => 1
            Thread-1 => 1
            Thread-2 => 1
            Thread-1 => 2
            Thread-0 => 2
            Thread-1 => 3
            Thread-2 => 2
            Thread-0 => 3
            Thread-2 => 3
         */
    }

}

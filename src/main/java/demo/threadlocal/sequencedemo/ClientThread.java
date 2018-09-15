package demo.threadlocal.sequencedemo;

import demo.threadlocal.sequencedemo.sequence.Sequence;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 客户端线程
 */
public class ClientThread extends Thread {

    // 序列
    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
        }
    }
}

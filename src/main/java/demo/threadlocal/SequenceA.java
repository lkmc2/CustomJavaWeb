package demo.threadlocal;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 序列A
 */
public class SequenceA implements Sequence {

    private static int number = 0;

    @Override
    public int getNumber() {
        number = number + 1;
        return number ;
    }

}

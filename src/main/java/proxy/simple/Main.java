package proxy.simple;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 主测试类
 */
public class Main {

    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Jack");

        /*
            运行结果：
            Before
            Hello!Jack
            After
         */
    }

}

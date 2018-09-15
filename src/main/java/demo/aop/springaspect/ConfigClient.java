package demo.aop.springaspect;


import demo.aop.springaspect.entity.smile.Smile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 配置客户端
 */
public class ConfigClient {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo/aop/springaspect/springaspectconfig.xml");

        Smile smileImpl = (Smile) context.getBean("smileImpl");
        smileImpl.smile("Jack");


        // 这里使用xml配置文件对切面进行拦截并处理

        /*
            运行结果：
            Before
            I am running! Jack
            After
         */
    }

}

package demo.aop.springaspect;


import demo.aop.springaspect.entity.show.impl.ShowingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 标签客户端
 */
public class TagClient {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo/aop/springaspect/springaspect.xml");

        ShowingImpl showingImpl = (ShowingImpl) context.getBean("showingImpl");
        showingImpl.showing("Swift");
        showingImpl.laughing("Wang");

        // 这里将对带有@Tag注解的laughing方法进行拦截，并在其执行前后进行处理

        /*
            运行结果：
            I am showing! Swift
            Before
            Let's start laugh! Wang
            After
         */
    }

}

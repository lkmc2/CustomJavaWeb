package demo.cxf;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 打招呼服务实现类
 */
@WebService
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String name) {
        return "hello " + name;
    }

}

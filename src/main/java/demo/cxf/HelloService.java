package demo.cxf;

import javax.jws.WebService;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 打招呼服务接口
 */
@WebService
public interface HelloService {
    String say(String name);
}

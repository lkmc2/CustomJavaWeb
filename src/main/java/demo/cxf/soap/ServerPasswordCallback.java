package demo.cxf.soap;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 服务器密码验证回调
 */
@Component
public class ServerPasswordCallback implements CallbackHandler {

    // 模拟服务器存储用户信息
    private static final Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("client", "123");
        userMap.put("server", "456");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];

        // 客户端用户名
        String clientUsername = callback.getIdentifier();

        // 服务器上用户名对应的密码
        String serverPassword = userMap.get(clientUsername);

        if (serverPassword != null) {
            // 给回调接口设置服务器密码
            callback.setPassword(serverPassword);
        }
    }

}

package demo.cxf.soap;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 客户端密码验证回调
 */
public class ClientPasswordCallback implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];

        // 设置密码
        callback.setPassword("123");
    }

}

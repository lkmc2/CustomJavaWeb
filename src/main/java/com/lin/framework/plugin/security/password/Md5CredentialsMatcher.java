package com.lin.framework.plugin.security.password;

import com.lin.framework.util.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description MD5密码匹配器
 */
public class Md5CredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 获取从表单提交过来的明文密码
        String submitted = String.valueOf((UsernamePasswordToken) token);

        // 获取数据库中存储的密码，已进行MD5加密
        String encrypted = String.valueOf(info.getCredentials());

        return CodecUtil.md5(submitted).equals(encrypted);
    }

}

package demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description Shiro例子
 */
public class HelloShiro {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        // 读取ini配置文件
        IniSecurityManagerFactory factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        // 初始化SecurityManager
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();

        // 创建用户密码认证（需要和ini配置文件中一致才能登陆成功）
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");

        try {
            // 使用用户名和密码登陆当前用户
            subject.login(token);
        } catch (AuthenticationException e) {
            LOGGER.error("登陆失败！");
            return;
        }

        // getPrincipal获取用户名
        LOGGER.info("登陆成功！ Hello " + subject.getPrincipal());

        // 注销用户
        subject.logout();

        /*
            运行结果：
            登陆成功！ Hello jack
         */
    }

}

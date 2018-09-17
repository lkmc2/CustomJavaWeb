package com.lin.framework.plugin.security;

import com.lin.framework.plugin.security.realm.SmartCustomRealm;
import com.lin.framework.plugin.security.realm.SmartJdbcRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;


import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 安全过滤器
 */
public class SmartSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();

        WebSecurityManager securityManager = super.getSecurityManager();
        // 设置Realm，可同时支持多个Realm，并按照先后顺序用括号分割
        setRealms(securityManager);
        // 设置Cache，用于减少数据库查询次数，降低I/O访问
        setCache(securityManager);
    }

    /**
     * 设置Realm
     */
    private void setRealms(WebSecurityManager webSecurityManager) {
        // 读取 smart.plugin.security.realms 配置项
        String securityRealms = SecurityConfig.getRealms();

        if (webSecurityManager != null) {
            // 根据逗号进行拆分
            String[] securityRealmArray = securityRealms.split(",");

            if (securityRealmArray.length > 0) {
                // 使Realm具有唯一性和顺序性
                Set<Realm> realms = new LinkedHashSet<>();

                for (String securityRealm : securityRealmArray) {
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
                        // 添加基于JDBC的Realm到集合
                        addJdbcRealm(realms);
                    } else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
                        // 添加自定义Realm到集合
                        addCustomRealm(realms);
                    }
                }

                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                // 设置Realm
                realmSecurityManager.setRealms(realms);
            }
        }
    }

    /**
     * 添加基于JDBC的Realm到集合
     */
    private void addJdbcRealm(Set<Realm> realms) {
        SmartJdbcRealm smartJdbcRealm = new SmartJdbcRealm();
        realms.add(smartJdbcRealm);
    }

    /**
     * 添加自定义Realm到集合
     */
    private void addCustomRealm(Set<Realm> realms) {
        // 读取 smart.plugin.security.custom.class 配置项，获取安全接口
        SmartSecurity smartSecurity = SecurityConfig.getSmartSecurity();

        // 添加自定义Realm
        SmartCustomRealm smartCustomRealm = new SmartCustomRealm(smartSecurity);
        realms.add(smartCustomRealm);
    }

    /**
     * 设置Cache缓存
     */
    private void setCache(WebSecurityManager webSecurityManager) {
        // 读取 smart.plugin.security.cache 配置项
        if (SecurityConfig.isCache()) {
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;

            // 使用基础内存的 CacheManager
            MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }

}

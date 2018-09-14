package com.lin.framework.util;

import com.lin.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 类操作工具类
 */
public final class ClassUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className 类名
     * @param isInitialized 是否实例化
     * @return 加载后的类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;

        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类失败");
            throw new RuntimeException(e);
        }

        return cls;
    }

    /**
     * 获取指定包名下的所有类
     * @param packageName 包名
     * @return 指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();

                if (url != null) {
                    String protocol = url.getProtocol();

                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        // 添加类到集合中
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();

                        if (jarUrlConnection != null) {
                            JarFile jarFile = jarUrlConnection.getJarFile();

                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();

                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();

                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        // 添加类到集合中
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("获取类集合失败", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    /**
     * 添加类到集合中
     * @param classSet 类集合
     * @param packagePath 包路径
     * @param packageName 包名
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        // 过滤出以.class结尾或是目录的文件
        File[] files = new File(packagePath).listFiles((file, name) ->
                (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());

        assert files != null;

        for (File file : files) {
            String fileName = file.getName();

            // 是文件
            if (file.isFile()) {
                // 获取类名
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                // 包名非空
                if (StringUtil.isNotEmpty(packageName)) {
                    // 给类名加上包名
                    className = packageName + "." + className;
                }

                // 添加类到集合中
                doAddClass(classSet, className);
            } else { // 是目录
                // 子包路径
                String subPackagePath = fileName;

                // 包路径非空
                if (StringUtil.isNotEmpty(packagePath)) {
                    // 给子包路径加上父包路径
                    subPackagePath = packagePath + "/" + subPackagePath;
                }

                // 子包名
                String subPackageName = fileName;

                // 包名非空
                if (StringUtil.isNotEmpty(packageName)) {
                    // 给子包名添加父包名
                    subPackageName = packageName + "." + subPackageName;
                }

                addClass(classSet, subPackagePath, subPackageName);
            }


        }
    }

    /**
     * 添加类到集合中
     * @param classSet 类集合
     * @param className 类名
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        // 加载类
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

}

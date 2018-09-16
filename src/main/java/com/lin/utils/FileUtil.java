package com.lin.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 文件操作工具类
 */
public final class FileUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取真实文件名（自动去掉文件路径）
     */
    public static String getRealFileName(String fileName) {
        return FilenameUtils.getName(fileName);
    }

    /**
     * 创建文件
     * @param filePath 文件路径
     * @return 新创建的文件
     */
    public static File createFile(String filePath) {
        File file;

        try {
            file = new File(filePath);

            // 获取父路径
            File parentDir = file.getParentFile();

            // 父路径不存在
            if (!parentDir.exists()) {
                // 强制创建父路径目录
                FileUtils.forceMkdir(parentDir);
            }
        } catch (IOException e) {
            LOGGER.error("创建文件失败", e);
            throw new RuntimeException(e);
        }

        return file;
    }

}

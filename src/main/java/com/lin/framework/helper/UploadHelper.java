package com.lin.framework.helper;

import com.lin.framework.bean.FileParam;
import com.lin.framework.bean.FormParam;
import com.lin.framework.bean.Param;
import com.lin.framework.util.StreamUtil;
import com.lin.utils.CollectionUtil;
import com.lin.utils.FileUtil;
import com.lin.utils.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 文件上传助手类
 * @since 1.0.0
 */
public final class UploadHelper {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    // Apache Commons FileUpload提供的Servlet文件上传对象
    private static ServletFileUpload servletFileUpload;

    /**
     * 初始化
     */
    public static void init(ServletContext servletContext) {
        // 获取上传目录
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        // 初始化上传对象
        servletFileUpload = new ServletFileUpload(
                new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));

        // 获取应用文件上传限制大小
        int uploadLimit = ConfigHelper.getAppUploadLimit();

        if (uploadLimit > 0) {
            // 设置上传对象的大小限制
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 判断请求是否为 Multipart 类型
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 创建请求对象
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        // 表单参数列表
        List<FormParam> formParamsList = new ArrayList<>();
        // 文件参数列表
        List<FileParam> fileParamList = new ArrayList<>();

        try {
            // 获取参数名和对应的文件项列表的映射
            Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);

            if (CollectionUtil.isNotEmpty(fileItemListMap)) {
                for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()) {
                    // 获取参数名和对应的文件项列表
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();

                    if (CollectionUtil.isNotEmpty(fileItemList)) {
                        // 遍历文件子项
                        for (FileItem fileItem : fileItemList) {
                            // 文件子项是表单字段
                            if (fileItem.isFormField()) {
                                // 获取参数值
                                String fieldValue = fileItem.getString("UTF-8");

                                // 添加表单参数到列表
                                formParamsList.add(new FormParam(fieldName, fieldValue));
                            } else {
                                // 文件子项不是表单字段（是文件）
                                // 获取文件名（不包括路径）
                                String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "UTF-8"));

                                if (StringUtil.isNotEmpty(fileName)) {
                                    // 获取文件大小、内容类型、输入流
                                    long fileSize = fileItem.getSize();
                                    String contentType = fileItem.getContentType();
                                    InputStream inputStream = fileItem.getInputStream();

                                    // 添加文件参数到列表
                                    fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            LOGGER.error("创建参数失败", e);
            throw new RuntimeException(e);
        }
        return new Param(formParamsList, fileParamList);
    }

    /**
     * 上传文件
     * @param basePath  基础上传路径
     * @param fileParam 文件参数
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        try {
            if (fileParam != null) {
                // 真实上传路径
                String filePath = basePath + fileParam.getFileName();

                // 创建文件
                FileUtil.createFile(filePath);

                // 创建输入流（上传文件参数中获取）
                BufferedInputStream inputStream = new BufferedInputStream(fileParam.getInputStream());

                // 创建输出流（本地服务器路径）
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));

                // 将输入流复制到输出流
                StreamUtil.copyStream(inputStream, outputStream);
            }
        } catch (Exception e) {
            LOGGER.error("上传文件失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量上传文件
     * @param basePath 基础上传路径
     * @param fileParamList 文件参数列表
     */
    public static void uploadFile(String basePath, List<FileParam> fileParamList) {
        try {
            if (CollectionUtil.isNotEmpty(fileParamList)) {
                for (FileParam fileParam : fileParamList) {
                    // 上传单个文件
                    uploadFile(basePath, fileParam);
                }
            }
        } catch (Exception e) {
            LOGGER.error("上传文件失败", e);
            throw new RuntimeException(e);
        }
    }

}

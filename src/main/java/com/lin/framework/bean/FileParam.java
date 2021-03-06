package com.lin.framework.bean;

import java.io.InputStream;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 封装上传文件参数
 */
public class FileParam {

    private String fieldName; // 参数名
    private String fileName; // 文件名
    private long fileSize; // 文件大小
    private String contentType; // 内容类型
    private InputStream inputStream; // 输入流

    public FileParam(String fieldName,
                     String fileName,
                     long fileSize,
                     String contentType,
                     InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}

package com.lin.framework.bean;

import com.lin.utils.CastUtil;
import com.lin.utils.CollectionUtil;
import com.lin.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 请求参数对象
 */
public class Param {

    // 表单参数列表
    private List<FormParam> formParamList;

    // 文件参数列表
    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    /**
     * 获取请求参数映射
     */
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<>();

        // 表单参数列表非空
        if (CollectionUtil.isNotEmpty(formParamList)) {
            for (FormParam formParam : formParamList) {
                // 获取参数名和参数值
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();

                // fieldMap已包含参数名
                if (fieldMap.containsKey(fieldName)) {
                    // 设置参数值为：参数名 + 分隔符 + 参数值
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }

        return fieldMap;
    }

    /**
     * 获取上传文件映射
     */
    public Map<String, List<FileParam>> getFileMap() {
        Map<String, List<FileParam>> fileMap = new HashMap<>();

        // 文件参数列表非空
        if (CollectionUtil.isNotEmpty(fileParamList)) {
            for (FileParam fileParam : fileParamList) {
                // 获取参数名
                String fieldName = fileParam.getFieldName();

                List<FileParam> fileParamList;

                if (fileMap.containsKey(fieldName)) {
                    // fileMap已包含该参数名，从fileMap中获取列表
                    fileParamList = fileMap.get(fieldName);
                } else {
                    // fileMap没包含该参数名，创建新的列表
                    fileParamList = new ArrayList<>();
                }
                // 文件参数添加到文件参数列表
                fileParamList.add(fileParam);

                // 存储参数名对应的文件参数列表
                fileMap.put(fieldName, fileParamList);
            }
        }

        return fileMap;
    }

    /**
     * 获取所有上传文件
     * @param fieldName 参数名
     * @return 指定参数名对应的所有上传文件
     */
    public List<FileParam> getFileList(String fieldName) {
        return getFileMap().get(fieldName);
    }

    /**
     * 获取唯一上传文件
     * @param fieldName 参数名
     * @return 指定参数名对应的唯一上传文件
     */
    public FileParam getFile(String fieldName) {
        // 获取参数名对应的所有上传文件
        List<FileParam> fileParamList = getFileList(fieldName);

        // 文件参数列表非空，并且文件数只有一个，返回唯一的文件参数
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1) {
            return fileParamList.get(0);
        }
        return null;
    }

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(formParamList)
                && CollectionUtil.isEmpty(fileParamList);
    }

    /**
     * 根据参数名获取 String 型参数值
     */
    public String getString(String name) {
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 double 型参数值
     */
    public double getDouble(String name) {
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 int 型参数值
     */
    public int getInt(String name) {
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 boolean 型参数值
     */
    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(getFieldMap().get(name));
    }

}

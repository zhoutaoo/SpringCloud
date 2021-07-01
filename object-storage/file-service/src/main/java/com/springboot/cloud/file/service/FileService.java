package com.springboot.cloud.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author fengdan
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储空间(桶)
     * @return file url
     */
    String uploadFile(MultipartFile file, String bucketName);

    /**
     * 删除文件
     *
     * @param bucketName 存储空间(桶)
     * @param objectName 文件名
     */
    void removeFile(String bucketName, String objectName);

    /**
     * 获取文件
     *
     * @param bucketName 存储空间(桶)
     * @param objectName 文件名
     * @return 进制流
     */
    InputStream getObject(String bucketName, String objectName);
}

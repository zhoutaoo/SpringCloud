package com.springboot.cloud.file.util;

import com.springboot.cloud.file.configure.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author fengdan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MinioFileUtil {

    private final MinioProperties minioProperties;

    private final MinioClient client;

    /**
     * 创建bucket
     *
     * @param bucketName 存储空间(桶)
     */
    public void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        BucketExistsArgs build = BucketExistsArgs.builder().bucket(bucketName).build();
        //判断 bucketName 是否存在
        if (!client.bucketExists(build)) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储空间(桶)
     * @return file url
     */
    public String uploadFile(MultipartFile file, String bucketName) throws
            IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //判断文件是否为空
        if (file == null || file.getSize() == 0) {
            return null;
        }
        //判断存储桶是否存在,不存在则创建
        createBucket(bucketName);
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assert originalFilename != null;
        //文件重名处理,bucketName+时间戳+随机数
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + format.format(new Date()) + "_" + new Random().nextInt(1000) +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //上传
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
        return minioProperties.getEndpoint() + "/" + bucketName + "/" + fileName;
    }

    /**
     * 删除文件
     *
     * @param bucketName 存储空间(桶)
     * @param objectName ⽂件名称
     */
    public void removeFile(String bucketName, String objectName) throws
            ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 获取⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return client.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }


}

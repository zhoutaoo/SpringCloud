package com.springboot.cloud.file.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.file.entity.FileObject;
import com.springboot.cloud.file.exception.MinioFileException;
import com.springboot.cloud.file.exception.MinioFileType;
import com.springboot.cloud.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author fengdan
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final FileService fileService;

    public FileController(@Qualifier("minioServiceImpl") FileService fileService) {
        this.fileService = fileService;
    }


    @ApiOperation(value = "上传文件", notes = "上传文件", response = Result.class)
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam(value = "file") MultipartFile file,
                            @RequestParam(value = "bucketName") String bucketName) {
        return Result.success(fileService.uploadFile(file, bucketName));
    }


    @ApiOperation(value = "删除文件", notes = "删除文件", response = Result.class)
    @DeleteMapping("/remove")
    public Result<?> remove(@Valid @RequestBody FileObject fileObject) {
        fileService.removeFile(fileObject.getBucketName(), fileObject.getFileName());
        return Result.success();
    }


    @ApiOperation(value = "预览图片", notes = "预览图片")
    @GetMapping("/previewPicture/{fileName}")
    public void previewPicture(@PathVariable("fileName") String objectName,
                               @RequestParam(value = "bucketName") String bucketName,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            InputStream stream = fileService.getObject(bucketName, objectName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            out.write(bytes);
            out.flush();
        }
    }


    @ApiOperation(value = "下载文件到本地", notes = "下载文件到本地")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName") String objectName,
                                           @RequestParam(value = "bucketName") String bucketName) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream stream = null;
        ByteArrayOutputStream output = null;
        try {
            stream = fileService.getObject(bucketName, objectName);
            if (stream == null) {
                throw new MinioFileException(MinioFileType.FAILED_DOWNLOAD_FILE, "");
            }
            //用于转换byte
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();

            //设置header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept-Ranges", "bytes");
            httpHeaders.add("Content-Length", bytes.length + "");
            objectName = new String(objectName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            //把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            httpHeaders.add("Content-disposition", "attachment; filename=" + objectName);
            httpHeaders.add("Content-Type", "text/plain;charset=utf-8");
            responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (output != null) {
                output.close();
            }
        }
        return responseEntity;
    }
}

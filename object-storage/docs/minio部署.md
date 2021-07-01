### Minio 服务器



- docker 部署

```docker
docker pull minio/minio

docker run -p 9000:9000 minio/minio server /data

默认:
Access Key: minioadmin
Secret Key: minioadmin
```
- docker 部署 MinIO自定义Access和Secret密钥 
  
  要覆盖MinIO的自动生成的密钥，您可以将Access和Secret密钥设为环境变量。 MinIO允许常规字符串作为Access和Secret密钥。
```docker
docker run -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
  -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
  -v /mnt/data:/data \
  -v /mnt/config:/root/.minio \
  minio/minio server /data
```



- macOS 部署
```linux
brew install minio/stable/minio
minio server /data
```



- Windows系统

  **下载二进制文件**

| 操作系统        | CPU架构 | 地址                                                         |
| --------------- | ------- | ------------------------------------------------------------ |
| 微软Windows系统 | 64位    | http://dl.minio.org.cn/server/minio/release/windows-amd64/minio.exe |

```text
minio.exe server D:\Photos
```



Minio 文档 : http://docs.minio.org.cn/docs


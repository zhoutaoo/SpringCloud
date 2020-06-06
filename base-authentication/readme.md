签权应用
----------

## 关键词

`签权、认证`

## 简介

签权微服务，可供网关gateway实现微服务对外权限的签定。

![授权签权服务架构](../../docs/auth.png)

## 启动

### 先决条件

- [postgresql](http://www.postgresql.org/)
- [rabbitmq](http://rabbitmq.io/download)
- [nacos](../../docs/register.md)

### 启动命令

进入应用目录

启动命令：`mvn spring-boot:run`

docker镜像打包：`mvn docker:build`




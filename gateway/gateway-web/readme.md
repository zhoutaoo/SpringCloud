网关应用
----------

## 关键词

`路由、网关`

## 简介

网关应用，提供网关路由转发、降级、熔断、请求处理等网关功能。

## 启动

### 先决条件

- [redis](http://redis.io/download)
- [postgresql](http://www.postgresql.org/)
- [rabbitmq](http://rabbitmq.io/download)
- [nacos](../../docs/register.md)

### 启动命令

进入应用目录

启动命令：`mvn spring-boot:run`

docker镜像打包：`mvn docker:build`

## 使用指南

### 路由功能 

网关的路由信息请通过管理端进行添加，只增加数据库是不能生效的。

[网关管理应用文档](../gateway-admin) 


请求通过网关时，网关会取redis中缓存的配置，结构如下图

![网关配置redis结构](../../docs/gateway-web.png) 


### API文档聚合

网关默认聚合了所有已在网关中配置过路由的应用的swagger文档

默认地址：http://localhost:8443/swagger-ui.html
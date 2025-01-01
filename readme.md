[![Build Status](https://travis-ci.org/zhoutaoo/SpringCloud.svg?branch=master)](https://travis-ci.org/zhoutaoo/SpringCloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


## 前言

**根据前期的使用和反馈，目前将脚手架整体进行了重构，发布了新的框架 Opensabre，请使用新版。**

💪Opensabre是基于SpringCloud2023的微服务开发平台，整合了Spring Security、Springcloud Alibaba等组件。

包含了基础的RBAC权限管理、授权认证、网关管理、服务治理、审计日志等系统管理基础应用。

定义了相关开发规范、风格并落地在服务框架层，开箱即用，支持Docker、Kubenetes的部署。

让项目开发人员快速进入业务开发，而不需过多时间花费在基础架构搭建和编码风格规范上。

目标是建立一套金融级、高安全性的微服务解决方案。

## 项目介绍

框架源码： `https://github.com/opensabre/opensabre-framework`

在线文档： `https://opensabre.github.io/docs`

## 功能特点

```
1. 统一Restful的响应报文，controll返回原始类型即可，无需手动包装，简化代码，可读性更好。

2. 统一异常处理，封装了基本的异常的响应，如参数检验、文件上传等。简化代码，更方便扩展。

3. 默认集成knife4j和Swagger 3.0 API文档，方便接口文档的传递、协作与调试。

4. 标准化WEB对象传递/转换/使用，方便统一开发风格，简化操作。

5. 框架/环境等元数据自动收集注册至properties和Nacos，方便系统运行时作为扩展判断，信息处理。

6. 系统启动时自动收集所有Restful url注册到权限资源，方便进行集中权限管理和授权使用。

7. 多机房/双活路由负载扩展支持，自定义路由和负载规则，更灵活、可控。

8. 默认引入spring validation，并扩展枚举、手机号等常用校验注解。

9. 默认引入日志trace、actuator等组件，统一日志打印格式。

10. 整体系统化为三层，framework框架、framework组件、基础应用，层次更清楚，结构更合理。

11. 配置中心，划分框架全局配置与应用配置（熔断降级、网关路由），配置项支持加密处理。

12. 支持日志敏感数据脱敏配置，响应报文敏感数据注解胶敏。
```

## 快速开始

### 先决条件

首先本机先要安装以下环境，建议先学习了解springboot和springcloud基础知识。

依赖说明: `https://opensabre.github.io/docs/#/framework/introduction/dependencies`

工程介绍：`https://opensabre.github.io/docs/#/framework/introduction/PROJECT`

### 快速入门

本工程是一个聚合工程，相关模块引用了 `https://github.com/opensabre` 的相关模块

1. 学习源码请克隆代码库： `git clone https://github.com/zhoutaoo/SpringCloud.git --recursive`

2. 快速使用框架开发请参考：`https://opensabre.github.io/docs/#/framework/manual/QUICKSTART`

### 基础应用使用

  **基础应用脚本**

* 1.创建数据库及表

路径一般为：应用/src/main/resources/db

如：`base-origanization/src/main/resources/db` 下的脚本，请先执行db文件创建库，再执行ddl建立表结构后再执行dml数据初使化

* 2.启动应用

根据自己需要，启动相应服务进行测试，cd 进入相关应用目录，执行命令： `mvn spring-boot:run` 或者通过ide提供的运行功能。

* 3.测试验证

可通过命令行或postman类的工具进行请求，应用端口默认8080

```shell
root@xxxxx # curl http://localhost:8080/test/echo?name=zhangsan

{   
    "code":"000000",
    "mesg":"处理成功",
    "time":"2022-11-22T14:46:58.643Z",
    "data":"Hello:zhangsan"
}

```
默认文档地址如下：

swagger文档地址：http://localhost:8080/swagger-ui/index.html

knife4j文档地址：http://localhost:8080/doc.html

## 架构与开发

[系统架构](https://opensabre.github.io/docs/#/framework/architecture/README)

## 功能与特性

### 功能预览

**用户管理**
 ![用户管理](https://user-images.githubusercontent.com/3946731/67155765-93d5ca00-f347-11e9-8114-44ac5ba3d05b.png)
 
 **角色管理**
 ![角色管理](https://user-images.githubusercontent.com/3946731/67155755-7c96dc80-f347-11e9-9b0a-e13b51167422.png)
 
 **服务容错**
 ![服务容错](https://user-images.githubusercontent.com/3946731/67155757-88829e80-f347-11e9-8750-d5c4eef7730e.png)
 
 **API文档**
 ![API文档](https://user-images.githubusercontent.com/3946731/67155763-8e787f80-f347-11e9-8347-ab2aeda6f7d6.png)
 
 **组织架构管理**
 ![组织架构管理](https://user-images.githubusercontent.com/3946731/67155751-69840c80-f347-11e9-8d88-e6fa4d6b7d23.png)

### 基础服务

| 服务   | 使用技术                     | 进度 | 备注                              |
|------|--------------------------|----|---------------------------------|
| 注册中心 | Nacos                    | ✅  |                                 |
| 配置中心 | Nacos                    | ✅  |                                 |
| 消息总线 | SpringCloud Bus+Rabbitmq | ✅  |                                 |
| 动态网关 | SpringCloud Gateway      | ✅  | 多种维度的流量控制（服务、IP、用户等），后端可配置化🏗   |
| 授权认证 | Spring Security OAuth2   | ✅  | Jwt模式                           |
| 服务容错 | SpringCloud Sentinel     | ✅  |                                 |
| 服务调用 | SpringCloud OpenFeign    | ✅  |                                 |
| 对象存储 | Minio                    | 🏗 |                                 |
| 数据权限 |                          | 🏗 | 使用mybatis对原查询做增强，业务代码不用控制，即可实现。 |

## 更新日志

[版本说明](https://opensabre.github.io/docs/#/framework/VERSONS)

## 联系交流

### 加入贡献代码

请入群 [请戳这里](https://github.com/zhoutaoo/SpringCloud/wiki) 加群主微信。

### 请作者喝饮料

如果你觉的有帮助到您，可以请作者喝饮料，这样更有动力，谢谢。

<p>
  <img width="300" src="https://user-images.githubusercontent.com/3946731/67401177-58eec300-f5e1-11e9-97e6-9ae29e3523e0.jpeg" alt="zfb">
  
  <img width="300" src="https://user-images.githubusercontent.com/3946731/67401198-60ae6780-f5e1-11e9-8c30-c12bd598011b.jpeg" alt="wx">
</p>

### 学习交流

Email：zhoutaoo@foxmail.com

**问问题的三要素**

1. 说明背景，使用了哪个模块，要做什么？ 

2. 怎么输入或操作的得到了什么结果？ 截图，日志

3. 哪里不明白或有什么疑问 ？

## Stargazers over time
 
[![Stargazers over time](https://starchart.cc/zhoutaoo/SpringCloud.svg)](https://starchart.cc/zhoutaoo/SpringCloud)

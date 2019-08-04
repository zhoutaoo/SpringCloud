WEB公共包
----------

## 简介

主要封装一些WEB开发用到的通用公共类、工具类，如公共web拦截器、web统一异常定义等。

## 使用

进入应用目录

安装命令：`mvn install`

## 使用指南

### 应用引入

需要将编译生成的jar包安装到本地maven类进入引用使用。

pom.xml

```
<dependency>
    <groupId>com.springboot.cloud</groupId>
    <artifactId>web</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
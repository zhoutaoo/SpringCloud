消息中心应用
----------

## 关键词

`消息中心、mq、rabbitmq、Spring Cloud Bus、消息总线`

## 简介

分布式中的其他服务都连接到消息系统，并且实现消息的生产、监听、和消费，可以用来建立一个多个应用之间的通信频道，以达到系统解耦、流量削锋、异步通讯等，本例中使用的是rabbitMQ，你也可以换成其它的如Kafka、RocketMq等

利用消息中心的机制可以做很多的事情，比如日志、指标的收集等，其中配置中心客户端刷新就是典型的应用场景之一，详情请见[配置中心文档](./center/config)。

## 启动

### 先决条件

- [rabbitmq](http://rabbitmq.io/download)

### 启动命令

进入应用目录

启动命令：`mvn spring-boot:run`

docker镜像打包：`mvn docker:build`
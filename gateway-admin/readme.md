网关管理应用
----------

## 关键词

`动态路由、动态网关`

## 简介

网关的管理服务，提供网关路由配置的增加、修改、删除等管理功能。

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

### 新增路由

接口用途： 通过网关后台动态新增路由或配置路由策略，如限流、转发、处理等网关动作

接口路径：`POST /gateway/routes`

报文类型：`application/json`

请求参数：

| 服务名              |   简介              |  默认地址                |
|--------------------|---------------------|--------------------------|
| uri                | 必填                |  代理路径，如http://baidu.com或lb://serviceId   |
| routeId            | 必填                |  路由名称，不可重复      |
| predicates         | 必填                |  断言，有多种断言维度，springcloud gateway默认有多种实现,见文档 https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.0.2.RELEASE/single/spring-cloud-gateway.html#gateway-request-predicates-factories,见例子            |
| filters            | 非必填              |  过滤器配置，springcloud gateway默认有多种实现，见文档 https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.0.2.RELEASE/single/spring-cloud-gateway.html#_gatewayfilter_factories,见例子      |
| description        | 非必填              |  路由描述                |

例子：

#### predicates:

```
 [{
    name:"Path",   
    args:{
    	"pattern":"配置路径"
    }
 }]
 ```
 
#### filters:
 
 ```
 [{
	name:"AddRequestHeader",   
    args:{
    	"pattern":"X-Request-Foo, Bar"
    }
 }]
```


### 转发路由例子

请求报文

```
{
    "uri": "http://www.baidu.com",
    "routeId": "test_route_id",
    "predicates": [{
        "name": "Path",
        "args": {
            "pattern": "/bd"
        }
    }],
    "filters":[{
    	"name": "StripPrefix",
        "args": {
            "parts": "2"
        }
    }],
    "description": "这是一个转发XX服务的路由"
}
```
响应报文

```
{
    "code": "000000",
    "mesg": "处理成功",
    "timestamp": "2019-01-31T07:19:53.230Z",
    "data": 1
}
```

### 限流路由例子

请求报文

```
{
    "uri": "lb://serviceid",
    "routeId": "xx_routeid",
    "predicates": [{
    	"name": "Path",
        "args": {
            "pattern": "/xx"
        }
    }],
    "filters":[{
        "name": "RequestRateLimiter",
        "args": {
            "redis-rate-limiter.replenishRate": "10",
            "redis-rate-limiter.burstCapacity": "20"
        }
    }],
    "description": "这是一个带限流的路由"
}
```

响应报文

```
{
    "code": "000000",
    "mesg": "处理成功",
    "timestamp": "2019-01-31T07:19:53.230Z",
    "data": 1
}
```



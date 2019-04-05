网关子项目简介
---------

## 关键词

`springcloud gateway、RateLimiter（限流）、动态路由、网关

## 简介

SpringCloud Gateway是基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建，目标是用于替代zuul。本项目网关管理端应用如下：

| 服务名             |   简介              |  默认地址                |
|--------------------|---------------------|--------------------------|
| gateway-admin      | 网关管理应用        |  http://localhost:8445   |
| gateway-web        | 网关入口应用        |  http://localhost:8443   |

gateway-admin下 `src/main/db` 脚本是动态路由的存储结构，启动项目前先建立好库和表


##  路由管理

### 新增路由

接口用途： 通过网关后台动态新增路由或配置路由策略，如限流、转发、处理等网关动作

接口路径：`POST /gateway/routes`

报文类型：`application/json`

请求参数：

| 服务名             |   简介              |  默认地址                |
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
    	"name": "Path",
        "args": {
            "pattern": "/bd"
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

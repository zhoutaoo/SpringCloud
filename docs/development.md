
### 项目目录结构

```
├── auth                           --授权认证子项目
│   ├── authentication-server        --认证组件服务端 
│   ├── authentication-client        --认证组件客户端 
│   ├── authorization-server         --授权组件服务端
│   ├── db                           --子项目公共数据库脚本
│   └── pom.xml                      --子项目maven配置文件
├── center               --中心子项目
│   ├── bus                --消息中心
│   └── pom.xml
├── common               --通用子项目
│   ├── core               --核心类库
│   ├── test               --测试工具类库
│   ├── web                --WEB核心类库
│   └── pom.xml          
├── gateway              --网关子项目  
│   ├── gateway-web        --基于springcloud gateway的网关
│   ├── gateway-admin      --springcloud gateway的网关管理模块
│   └── pom.xml
├── sysadmin             --系统管理子项目
│   ├── db                 --系统管理子项目数据库脚本
│   ├── organization       --组织管理应用，包括用户、角色、资源、菜单、组织架构的管理
│   └── pom.xml
├── monitor              --监控、日志及服务管理子项目
│   ├── admin              --springboot admin管理
│   ├── hystrix-dashboard  --hystrix监控
│   ├── turbine            --turbine监控聚集 
│   └── pom.xml
├── webapps              --web项目的基础父工程，定义常用依赖等
│   ├── webapp-parent      --web项目的父工程，新建业务应用父工程
│   └── pom.xml
├── docs                 --文档及资源文件
├── data                 --server及服务数据存储目录
│   ├── elasticsearch      --elasticsearch配置数据存储位置
│   ├── mysql              --mysql数据库文件存储目录 
│   ├── rabbitmq           --rabbitmq数据文件存储目录
│   └── redis              --redis数据文件存储目录
├── demos                --demos子项目，常用的解决方案案例
│   ├── consumer-feign     --消费者服务 feign demo
│   ├── consumer-ribbon    --消费者服务 ribbon demo 
│   ├── producer           --服务提供者，产品服务
│   ├── producer-jpa       --服务提供者，产品服务，jpa和hateoas
│   └── pom.xml
├── docker-compose       --基础服务docker快速启动方案
│   ├── .env                          --docker-compose环境变量配置文件
│   ├── apollo                        --apollo配置中心配置文件等
│   ├── devops                        --devops组件的相关配置文件目录
│   ├── nacos                         --macos组件配置文件目录
│   ├── docker-compose.yml            --docker compose配置文件，基础组件如数据库、redis、mq等组件 
│   ├── docker-compose.devops.yml     --docker compose配置文件，es、apm等devops组件 
│   ├── docker-compose.gateway.yml    --docker compose配置文件，网关相关组件
│   ├── docker-compose.nacos.yml      --docker compose配置文件，springcloud alibaba相关组件
│   └── docker-compose.config.yml     --docker compose配置文件，apollo配置中心 
├── readme.md            --readme文档入口
└── pom.xml              --业务服务子项目
```

### module目录结构

```
├── logs                     --日志目录
│   ├── spring.log
│   └── spring.log.2018-04-15.0.gz
├── pom.xml                  --module maven配置文件
├── src                      --源码目录
│   ├── main                   --源文件
│   │   ├── db                 --服务db脚本目录
│   │   │   └── db.sql           --ddl & dml
│   │   ├── docker             --docker相关配置文件
│   │   │   └── Dockerfile       --dockerfile
│   │   ├── docs               --接口文档目录，一般由swagger生成
│   │   ├── java               --java源码目录
│   │   │   ├── dao              --数据操作层
│   │   │   ├── service          --业务逻辑层
│   │   │   ├── provider         --调用第三方服务的提供类
│   │   │   ├── rest             --接口controller
│   │   │   ├── entity           --实体类
│   │   │   │   ├── form           --rest表单校验
│   │   │   │   ├── param          --dao参数，可以由form转化来
│   │   │   │   ├── po             --实体类
│   │   │   │   └── vo             --视图对象
│   │   │   ├── events           --事件或消息处理类
│   │   │   ├── config           --配置类
│   │   │   ├── exception        --异常处理相关类
│   │   │   ├── interceptor      --拦截器相关类
│   │   │   └── task             --定时任务
│   │   └── resources          --配置文件目录 
│   │       ├── application.yml  --springboot的应用配置文件
│   │       └── bootstrap.yml    --springboot的配置文件
│   └── test                   --测试目录
│       ├── java                 --java测试案例目录
│       └── resources          --配置文件目录 
│          └── application.yml   --springboot test的配置文件
└── target                     --编译目标目录
```
### 开发规范

[规范文档](pattern.md)
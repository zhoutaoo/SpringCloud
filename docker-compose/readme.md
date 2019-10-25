

## 启动基础服务

mysql、redis、rabbitmq
 
`docker-compose up -d`

rabbitmq默认账号密码：guest/guest
mysql默认账号密码：root/root123

## 启动devops组件，如elasticsearch、zipkin、skywalking、kibana、grafana

`docker-compose -f docker-compose.yml -f docker-compose.devops.yml up`

## 启动阿里注册中心和配置中心nacos

`docker-compose -f docker-compose.yml -f docker-compose.nacos.yml up`

默认账号密码：nacos/nacos

## 启动监控类服务，如springboot-admin、sentinel-dashboard

`docker-compose -f docker-compose.yml -f docker-compose.monitor.yml up`

默认账号密码：sentinel/sentinel

## 启动MOSS

`docker-compose -f docker-compose.yml -f docker-compose.moss.yml up moss`

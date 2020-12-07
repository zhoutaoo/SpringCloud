#!/usr/bin/env bash
echo '==================1.开发环境准备================================'
echo '1.1请确保安装并java8, git, maven并设置好环境变量'
echo '1.2请确保安装并docker,docker-compose并设置好环境变量'

#确认环境信息准备就绪
read -r -p "开发环境准备好了吗? [Y/n] " envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac

echo '==================1.3清理当前脚本启动的容器和产生的镜像(可选的)=============='
#清理当前脚本启动的容器和产生的镜像(可选的)
#docker stop sc-rabbitmq sc-redis sc-mysql
#docker rm sc-rabbitmq sc-redis sc-mysql
#docker image rm rabbitmq:alpine redis:alpine mysql:9.6-alpine

#docker stop sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
#docker rm sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
#docker image rm cike/admin cike/authorization-server:latest cike/authentication-server:latest cike/organization:latest cike/gateway-admin:latest cike/gateway-web:latest

echo '==================2.安装认证公共包到本地maven仓库=================='
#安装认证公共包到本地maven仓库
cd common-core && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -

cd common-web && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -

echo '==================3.安装认证客户端到本地maven仓库=================='
#安装认证客户端到本地maven仓库
cd base-authclient && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -

echo '==================4.docker-compose启动公共服务==================='
#去docker-compose目录
cd docker-compose
echo '==================4.1显示环境变量: docker-compose/.env =========='
#显示环境变量
cat ./.env
echo ''

#按需要开启公共服务
echo '==================4.2启动 mysql or redis or rabbitmq or 注册中心========'
#启动mysql
docker-compose -f docker-compose.yml up -d mysql
#启动redis
docker-compose -f docker-compose.yml up -d redis
#启动rabbitmq
docker-compose -f docker-compose.yml up -d rabbitmq
#启动注册中心、配置中心
docker-compose -f docker-compose.yml up -d nacos

echo '当前目录:' && pwd

#回到根目录
cd -

#数据库初始化
echo '==================4.3初始化 mysql ========'
#去docker-compose目录
cd docker-compose
docker-compose -f docker-compose.yml up mysql-init

echo '当前目录:' && pwd

#回到根目录
cd -

echo '==================4.4.构建镜像: 消息中心========'

#构建镜像:消息中心
cd center-bus
mvn package && mvn docker:build

#回到根目录
cd -

echo '==================5.构建镜像并启动网关(gateway)相关服务==============='
#构建镜像:网关服务
cd gateway-web
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 gateway-web'

#构建镜像:网关管理服务
cd gateway-admin
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 gateway-admin'

echo '==================6.构建镜像并启动组织(organization)相关服务=================='
#构建镜像:组织服务
cd base-organization
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 organization'

echo '==================7.构建镜像并启动认证(auth)相关服务=================='
#构建镜像:认证服务
cd base-authentication
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 authentication-server'

#构建镜像:授权服务
cd base-authorization
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 authorization-server'

echo '==================8.构建镜像并启动监控(monitor)相关服务==============='
#构建镜像:管理台服务
cd monitor-admin
mvn package && mvn docker:build

#回到根目录
cd -

echo '请自行根据需通过dokcer要启动 Admin'


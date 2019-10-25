SET NAMES utf8;

DROP DATABASE IF EXISTS sc_auth;
CREATE DATABASE sc_auth DEFAULT CHARSET utf8mb4;
USE sc_auth;

-- access_token存储表
DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token
(
    token_id          VARCHAR(256) COMMENT 'MD5加密的access_token的值',
    token             BLOB COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
    authentication_id VARCHAR(256) COMMENT 'MD5加密过的username,client_id,scope',
    user_name         VARCHAR(256) COMMENT '登录的用户名',
    client_id         VARCHAR(256) COMMENT '客户端ID',
    authentication    BLOB COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
    refresh_token     VARCHAR(256) COMMENT 'MD5加密果的refresh_token的值'
) COMMENT '访问令牌表';

-- refresh_token存储表
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token
(
    token_id       VARCHAR(256) COMMENT 'MD5加密过的refresh_token的值',
    token          BLOB COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据',
    authentication BLOB COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) COMMENT '更新令牌表';

-- 授权记录表
DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals
(
    userid         VARCHAR(256) COMMENT '登录的用户名',
    clientid       VARCHAR(256) COMMENT '客户端ID',
    scope          VARCHAR(256) COMMENT '申请的权限',
    status         VARCHAR(10) COMMENT '状态（Approve或Deny）',
    expiresat      DATETIME COMMENT '过期时间',
    lastmodifiedat DATETIME COMMENT '最终修改时间'
) COMMENT '授权记录表';

-- 授权码表
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code
(
    code           VARCHAR(256) COMMENT '授权码(未加密)',
    authentication BLOB COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) COMMENT '授权码表';

-- client用户表
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(256) NOT NULL COMMENT '客户端ID',
    resource_ids            VARCHAR(256) COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
    client_secret           VARCHAR(256) COMMENT '客户端密匙',
    scope                   VARCHAR(256) COMMENT '客户端申请的权限范围',
    authorized_grant_types  VARCHAR(256) COMMENT '客户端支持的grant_type',
    web_server_redirect_uri VARCHAR(256) COMMENT '重定向URI',
    authorities             VARCHAR(256) COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
    access_token_validity   INTEGER COMMENT '访问令牌有效时间值(单位:秒)',
    refresh_token_validity  INTEGER COMMENT '更新令牌有效时间值(单位:秒)',
    additional_information  VARCHAR(4096) COMMENT '预留字段',
    autoapprove             VARCHAR(256) COMMENT '用户是否自动Approval操作',
    CONSTRAINT pk_oauth_client_details_client_id PRIMARY KEY (client_id)
) COMMENT '客户端信息';

-- 客户端授权令牌表
DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token
(
    token_id          VARCHAR(256) COMMENT 'MD5加密的access_token值',
    token             BLOB COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
    authentication_id VARCHAR(256) COMMENT 'MD5加密过的username,client_id,scope',
    user_name         VARCHAR(256) COMMENT '登录的用户名',
    client_id         VARCHAR(256) COMMENT '客户端ID'
) COMMENT '客户端授权令牌表';

-- DML数据准备

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('test_client', NULL, '$2a$10$2szDKjvKHJCWE6YQNznogOeQF3USZHmCYj1fG7YbfK.vnTgNKLzri', 'read', 'client_credentials,authorization_code,mobile,password,refresh_token', 'http://baidu.com', NULL, 7200, 108000, NULL, NULL);



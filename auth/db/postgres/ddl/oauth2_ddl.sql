--access_token存储表
DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token
(
  token_id          CHARACTER VARYING(256), -- MD5加密的access_token的值
  token             BYTEA, -- OAuth2AccessToken.java对象序列化后的二进制数据
  authentication_id CHARACTER VARYING(256), -- MD5加密过的username,client_id,scope
  user_name         CHARACTER VARYING(256), -- 登录的用户名
  client_id         CHARACTER VARYING(256), -- 客户端ID
  authentication    BYTEA, -- OAuth2Authentication.java对象序列化后的二进制数据
  refresh_token     CHARACTER VARYING(256) -- MD5加密果的refresh_token的值
);
COMMENT ON TABLE oauth_access_token IS '访问令牌表';
COMMENT ON COLUMN oauth_access_token.token_id IS 'MD5加密的access_token的值';
COMMENT ON COLUMN oauth_access_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_access_token.authentication_id IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN oauth_access_token.user_name IS '登录的用户名';
COMMENT ON COLUMN oauth_access_token.client_id IS '客户端ID';
COMMENT ON COLUMN oauth_access_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_access_token.refresh_token IS 'MD5加密果的refresh_token的值';
--refresh_token存储表
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token
(
  token_id       CHARACTER VARYING(256), -- MD5加密过的refresh_token的值
  token          BYTEA, -- OAuth2RefreshToken.java对象序列化后的二进制数据
  authentication BYTEA -- OAuth2Authentication.java对象序列化后的二进制数据
);
COMMENT ON TABLE oauth_refresh_token IS '更新令牌表';
COMMENT ON COLUMN oauth_refresh_token.token_id IS 'MD5加密过的refresh_token的值';
COMMENT ON COLUMN oauth_refresh_token.token IS 'OAuth2RefreshToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_refresh_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';

--授权记录表
DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals
(
  userid         CHARACTER VARYING(256), -- 登录的用户名
  clientid       CHARACTER VARYING(256), -- 客户端ID
  scope          CHARACTER VARYING(256), -- 申请的权限
  status         CHARACTER VARYING(10), -- 状态（Approve或Deny）
  expiresat      TIMESTAMP WITHOUT TIME ZONE, -- 过期时间
  lastmodifiedat TIMESTAMP WITHOUT TIME ZONE -- 最终修改时间
);
COMMENT ON TABLE oauth_approvals IS '授权记录表';
COMMENT ON COLUMN oauth_approvals.userid IS '登录的用户名';
COMMENT ON COLUMN oauth_approvals.clientid IS '客户端ID';
COMMENT ON COLUMN oauth_approvals.scope IS '申请的权限';
COMMENT ON COLUMN oauth_approvals.status IS '状态（Approve或Deny）';
COMMENT ON COLUMN oauth_approvals.expiresat IS '过期时间';
COMMENT ON COLUMN oauth_approvals.lastmodifiedat IS '最终修改时间';
--授权码表
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code
(
  code           CHARACTER VARYING(256), -- 授权码(未加密)
  authentication BYTEA -- AuthorizationRequestHolder.java对象序列化后的二进制数据
);
COMMENT ON TABLE oauth_code IS '授权码表';
COMMENT ON COLUMN oauth_code.code IS '授权码(未加密)';
COMMENT ON COLUMN oauth_code.authentication IS 'AuthorizationRequestHolder.java对象序列化后的二进制数据';

--client用户表
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details
(
  client_id               CHARACTER VARYING(256) NOT NULL, -- 客户端ID
  resource_ids            CHARACTER VARYING(256), -- 资源ID集合,多个资源时用逗号(,)分隔
  client_secret           CHARACTER VARYING(256), -- 客户端密匙
  scope                   CHARACTER VARYING(256), -- 客户端申请的权限范围
  authorized_grant_types  CHARACTER VARYING(256), -- 客户端支持的grant_type
  web_server_redirect_uri CHARACTER VARYING(256), -- 重定向URI
  authorities             CHARACTER VARYING(256), -- 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
  access_token_validity   INTEGER, -- 访问令牌有效时间值(单位:秒)
  refresh_token_validity  INTEGER, -- 更新令牌有效时间值(单位:秒)
  additional_information  CHARACTER VARYING(4096), -- 预留字段
  autoapprove             CHARACTER VARYING(256), -- 用户是否自动Approval操作
  CONSTRAINT pk_oauth_client_details_client_id PRIMARY KEY (client_id)
);

COMMENT ON TABLE oauth_client_details IS '客户端信息';
COMMENT ON COLUMN oauth_client_details.client_id IS '客户端ID';
COMMENT ON COLUMN oauth_client_details.resource_ids IS '资源ID集合,多个资源时用逗号(,)分隔';
COMMENT ON COLUMN oauth_client_details.client_secret IS '客户端密匙';
COMMENT ON COLUMN oauth_client_details.scope IS '客户端申请的权限范围';
COMMENT ON COLUMN oauth_client_details.authorized_grant_types IS '客户端支持的grant_type';
COMMENT ON COLUMN oauth_client_details.web_server_redirect_uri IS '重定向URI';
COMMENT ON COLUMN oauth_client_details.authorities IS '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔';
COMMENT ON COLUMN oauth_client_details.access_token_validity IS '访问令牌有效时间值(单位:秒)';
COMMENT ON COLUMN oauth_client_details.refresh_token_validity IS '更新令牌有效时间值(单位:秒)';
COMMENT ON COLUMN oauth_client_details.additional_information IS '预留字段';
COMMENT ON COLUMN oauth_client_details.autoapprove IS '用户是否自动Approval操作';

--客户端授权令牌表
DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token
(
  token_id          CHARACTER VARYING(256), -- MD5加密的access_token值
  token             BYTEA, -- OAuth2AccessToken.java对象序列化后的二进制数据
  authentication_id CHARACTER VARYING(256), -- MD5加密过的username,client_id,scope
  user_name         CHARACTER VARYING(256), -- 登录的用户名
  client_id         CHARACTER VARYING(256) -- 客户端ID
);
COMMENT ON TABLE oauth_client_token IS '客户端授权令牌表';
COMMENT ON COLUMN oauth_client_token.token_id IS 'MD5加密的access_token值';
COMMENT ON COLUMN oauth_client_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_client_token.authentication_id IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN oauth_client_token.user_name IS '登录的用户名';
COMMENT ON COLUMN oauth_client_token.client_id IS '客户端ID';



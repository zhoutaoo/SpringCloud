--用户表
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id                      VARCHAR(20) PRIMARY KEY,
  username                VARCHAR(100) NOT NULL,
  password                VARCHAR(100) NOT NULL,
  name                    VARCHAR(200) NOT NULL,
  mobile                  VARCHAR(20),
  deleted                 VARCHAR(1)   NOT NULL DEFAULT 'N',
  description             VARCHAR(500),
  enabled                 BOOLEAN,
  account_non_expired     BOOLEAN,
  credentials_non_expired BOOLEAN,
  account_non_locked      BOOLEAN,
  created_time            TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time            TIMESTAMP    NOT NULL DEFAULT now(),
  created_by              VARCHAR(100) NOT NULL,
  updated_by              VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ux_users_username
  ON users (username);
CREATE UNIQUE INDEX ux_users_mobile
  ON users (mobile);
COMMENT ON TABLE users IS '用户表';
COMMENT ON COLUMN users.id IS '用户id';
COMMENT ON COLUMN users.username IS '用户名';
COMMENT ON COLUMN users.password IS '用户密码密文';
COMMENT ON COLUMN users.name IS '用户姓名';
COMMENT ON COLUMN users.mobile IS '用户手机';
COMMENT ON COLUMN users.description IS '角色简介';
COMMENT ON COLUMN users.deleted IS '是否已删除Y：已删除，N：未删除';
COMMENT ON COLUMN users.enabled IS '是否有效用户';
COMMENT ON COLUMN users.account_non_expired IS '账号是否未过期';
COMMENT ON COLUMN users.credentials_non_expired IS '密码是否未过期';
COMMENT ON COLUMN users.created_time IS '创建时间';
COMMENT ON COLUMN users.updated_time IS '更新时间';
COMMENT ON COLUMN users.created_by IS '创建人';
COMMENT ON COLUMN users.updated_by IS '更新人';

--角色表
DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
  id           VARCHAR(20) PRIMARY KEY,
  code         VARCHAR(100) NOT NULL,
  name         VARCHAR(200) NOT NULL,
  description  VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE roles IS '角色表';
COMMENT ON COLUMN roles.id IS '角色id';
COMMENT ON COLUMN roles.code IS '角色编码';
COMMENT ON COLUMN roles.name IS '角色名称';
COMMENT ON COLUMN roles.description IS '角色简介';
COMMENT ON COLUMN roles.created_time IS '创建时间';
COMMENT ON COLUMN roles.updated_time IS '更新时间';
COMMENT ON COLUMN roles.created_by IS '创建人';
COMMENT ON COLUMN roles.updated_by IS '更新人';

--资源表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
  id           VARCHAR(20) PRIMARY KEY,
  code         VARCHAR(100) NOT NULL,
  type         VARCHAR(100) NOT NULL,
  name         VARCHAR(200) NOT NULL,
  url          VARCHAR(200) NOT NULL,
  method       VARCHAR(20)  NOT NULL,
  description  VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ux_resource_code
  ON resource (code);
COMMENT ON TABLE resource IS '资源表';
COMMENT ON COLUMN resource.id IS '资源id';
COMMENT ON COLUMN resource.name IS '资源名称';
COMMENT ON COLUMN resource.code IS '资源编码';
COMMENT ON COLUMN resource.type IS '资源类型';
COMMENT ON COLUMN resource.url IS '资源路径';
COMMENT ON COLUMN resource.method IS '资源方法';
COMMENT ON COLUMN resource.description IS '资源简介';
COMMENT ON COLUMN resource.created_time IS '创建时间';
COMMENT ON COLUMN resource.updated_time IS '更新时间';
COMMENT ON COLUMN resource.created_by IS '创建人';
COMMENT ON COLUMN resource.updated_by IS '更新人';

--用户和角色关系表
DROP TABLE IF EXISTS user_role_relation;
CREATE TABLE user_role_relation
(
  id           VARCHAR(20) PRIMARY KEY,
  user_id      VARCHAR(20)  NOT NULL,
  role_id      VARCHAR(20)  NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE user_role_relation IS '用户和角色关系表';
COMMENT ON COLUMN user_role_relation.id IS '关系id';
COMMENT ON COLUMN user_role_relation.user_id IS '用户id';
COMMENT ON COLUMN user_role_relation.role_id IS '角色id';
COMMENT ON COLUMN user_role_relation.created_time IS '创建时间';
COMMENT ON COLUMN user_role_relation.updated_time IS '更新时间';
COMMENT ON COLUMN user_role_relation.created_by IS '创建人';
COMMENT ON COLUMN user_role_relation.updated_by IS '更新人';

--角色和资源关系表
DROP TABLE IF EXISTS role_resource_relation;
CREATE TABLE role_resource_relation
(
  id           VARCHAR(20) PRIMARY KEY,
  resource_id  VARCHAR(20)  NOT NULL,
  role_id      VARCHAR(20)  NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE role_resource_relation IS '角色和资源关系表';
COMMENT ON COLUMN role_resource_relation.id IS '关系id';
COMMENT ON COLUMN role_resource_relation.role_id IS '角色id';
COMMENT ON COLUMN role_resource_relation.resource_id IS '资源id';
COMMENT ON COLUMN role_resource_relation.created_time IS '创建时间';
COMMENT ON COLUMN role_resource_relation.updated_time IS '更新时间';
COMMENT ON COLUMN role_resource_relation.created_by IS '创建人';
COMMENT ON COLUMN role_resource_relation.updated_by IS '更新人';
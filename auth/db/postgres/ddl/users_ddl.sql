--用户表
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id                      SERIAL PRIMARY KEY,
  username                VARCHAR(100) NOT NULL,
  password                VARCHAR(100) NOT NULL,
  name                    VARCHAR(200),
  mobile                  VARCHAR(20),
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
  id           SERIAL PRIMARY KEY,
  code         VARCHAR(100) NOT NULL,
  name         VARCHAR(200),
  description   VARCHAR(500),
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
DROP TABLE IF EXISTS resources;
CREATE TABLE resources
(
  id           SERIAL PRIMARY KEY,
  code         VARCHAR(100),
  type         VARCHAR(100),
  name         VARCHAR(200),
  url          VARCHAR(200),
  method       VARCHAR(20),
  description  VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ux_resources_code
  ON resources (code);
COMMENT ON TABLE resources IS '资源表';
COMMENT ON COLUMN resources.id IS '资源id';
COMMENT ON COLUMN resources.name IS '资源名称';
COMMENT ON COLUMN resources.description IS '资源简介';
COMMENT ON COLUMN resources.created_time IS '创建时间';
COMMENT ON COLUMN resources.updated_time IS '更新时间';
COMMENT ON COLUMN resources.created_by IS '创建人';
COMMENT ON COLUMN resources.updated_by IS '更新人';

--用户和角色关系表
DROP TABLE IF EXISTS users_roles_relation;
CREATE TABLE users_roles_relation
(
  id           SERIAL PRIMARY KEY,
  user_id      INT          NOT NULL,
  role_id      INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE users_roles_relation IS '用户和角色关系表';
COMMENT ON COLUMN users_roles_relation.id IS '关系id';
COMMENT ON COLUMN users_roles_relation.user_id IS '用户id';
COMMENT ON COLUMN users_roles_relation.role_id IS '角色id';
COMMENT ON COLUMN users_roles_relation.created_time IS '创建时间';
COMMENT ON COLUMN users_roles_relation.updated_time IS '更新时间';
COMMENT ON COLUMN users_roles_relation.created_by IS '创建人';
COMMENT ON COLUMN users_roles_relation.updated_by IS '更新人';

--角色和资源关系表
DROP TABLE IF EXISTS roles_resources_relation;
CREATE TABLE roles_resources_relation
(
  id           SERIAL PRIMARY KEY,
  resource_id  INT          NOT NULL,
  role_id      INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE roles_resources_relation IS '角色和资源关系表';
COMMENT ON COLUMN roles_resources_relation.id IS '关系id';
COMMENT ON COLUMN roles_resources_relation.role_id IS '角色id';
COMMENT ON COLUMN roles_resources_relation.resource_id IS '资源id';
COMMENT ON COLUMN roles_resources_relation.created_time IS '创建时间';
COMMENT ON COLUMN roles_resources_relation.updated_time IS '更新时间';
COMMENT ON COLUMN roles_resources_relation.created_by IS '创建人';
COMMENT ON COLUMN roles_resources_relation.updated_by IS '更新人';
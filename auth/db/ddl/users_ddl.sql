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

--用户组表
DROP TABLE IF EXISTS groups;
CREATE TABLE groups
(
  id           SERIAL PRIMARY KEY,
  parent_id    INT          NOT NULL,
  name         VARCHAR(200),
  description   VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE groups IS '用户组表';
COMMENT ON COLUMN groups.id IS '用户组id';
COMMENT ON COLUMN groups.parent_id IS '用户组父id';
COMMENT ON COLUMN groups.name IS '用户组名称';
COMMENT ON COLUMN groups.description IS '用户组简介';
COMMENT ON COLUMN groups.created_time IS '创建时间';
COMMENT ON COLUMN groups.updated_time IS '更新时间';
COMMENT ON COLUMN groups.created_by IS '创建人';
COMMENT ON COLUMN groups.updated_by IS '更新人';

--用户和组关系表
DROP TABLE IF EXISTS users_groups_relation;
CREATE TABLE users_groups_relation
(
  id           SERIAL PRIMARY KEY,
  user_id      INT          NOT NULL,
  group_id     INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE users_groups_relation IS '用户和组关系表';
COMMENT ON COLUMN users_groups_relation.id IS '关系id';
COMMENT ON COLUMN users_groups_relation.user_id IS '用户id';
COMMENT ON COLUMN users_groups_relation.group_id IS '用户组id';
COMMENT ON COLUMN users_groups_relation.created_time IS '创建时间';
COMMENT ON COLUMN users_groups_relation.updated_time IS '更新时间';
COMMENT ON COLUMN users_groups_relation.created_by IS '创建人';
COMMENT ON COLUMN users_groups_relation.updated_by IS '更新人';

--岗位表
DROP TABLE IF EXISTS positions;
CREATE TABLE positions
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(200),
  description   VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE positions IS '岗位表';
COMMENT ON COLUMN positions.id IS '岗位id';
COMMENT ON COLUMN positions.name IS '岗位名称';
COMMENT ON COLUMN positions.description IS '岗位简介';
COMMENT ON COLUMN positions.created_time IS '创建时间';
COMMENT ON COLUMN positions.updated_time IS '更新时间';
COMMENT ON COLUMN positions.created_by IS '创建人';
COMMENT ON COLUMN positions.updated_by IS '更新人';

--用户和角色系表
DROP TABLE IF EXISTS users_positions_relation;
CREATE TABLE users_positions_relation
(
  id           SERIAL PRIMARY KEY,
  user_id      INT          NOT NULL,
  position_id  INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE users_positions_relation IS '用户和角色关系表';
COMMENT ON COLUMN users_positions_relation.id IS '关系id';
COMMENT ON COLUMN users_positions_relation.user_id IS '用户id';
COMMENT ON COLUMN users_positions_relation.position_id IS '角色id';
COMMENT ON COLUMN users_positions_relation.created_time IS '创建时间';
COMMENT ON COLUMN users_positions_relation.updated_time IS '更新时间';
COMMENT ON COLUMN users_positions_relation.created_by IS '创建人';
COMMENT ON COLUMN users_positions_relation.updated_by IS '更新人';

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

--菜单表
DROP TABLE IF EXISTS menus;
CREATE TABLE menus
(
  id           SERIAL PRIMARY KEY,
  parent_id    INT          NOT NULL,
  type         VARCHAR(100),
  href         VARCHAR(200),
  icon         VARCHAR(200),
  name         VARCHAR(200),
  description   VARCHAR(500),
  order_num    INTEGER,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE menus IS '菜单表';
COMMENT ON COLUMN menus.id IS '菜单id';
COMMENT ON COLUMN menus.parent_id IS '父菜单id';
COMMENT ON COLUMN menus.name IS '菜单名称';
COMMENT ON COLUMN menus.type IS '菜单类型';
COMMENT ON COLUMN menus.href IS '菜单路径';
COMMENT ON COLUMN menus.icon IS '菜单图标';
COMMENT ON COLUMN menus.description IS '菜单简介';
COMMENT ON COLUMN menus.created_time IS '创建时间';
COMMENT ON COLUMN menus.updated_time IS '更新时间';
COMMENT ON COLUMN menus.created_by IS '创建人';
COMMENT ON COLUMN menus.updated_by IS '更新人';

--角色和菜单关系表
DROP TABLE IF EXISTS roles_menus_relation;
CREATE TABLE roles_menus_relation
(
  id           SERIAL PRIMARY KEY,
  menu_id      INT          NOT NULL,
  role_id      INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE roles_menus_relation IS '角色和菜单关系表';
COMMENT ON COLUMN roles_menus_relation.id IS '关系id';
COMMENT ON COLUMN roles_menus_relation.role_id IS '角色id';
COMMENT ON COLUMN roles_menus_relation.menu_id IS '菜单id';
COMMENT ON COLUMN roles_menus_relation.created_time IS '创建时间';
COMMENT ON COLUMN roles_menus_relation.updated_time IS '更新时间';
COMMENT ON COLUMN roles_menus_relation.created_by IS '创建人';
COMMENT ON COLUMN roles_menus_relation.updated_by IS '更新人';

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
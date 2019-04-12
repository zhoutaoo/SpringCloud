--用户组表
DROP TABLE IF EXISTS "group";
CREATE TABLE "group"
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
COMMENT ON TABLE "group" IS '用户组表';
COMMENT ON COLUMN "group".id IS '用户组id';
COMMENT ON COLUMN "group".parent_id IS '用户组父id';
COMMENT ON COLUMN "group".name IS '用户组名称';
COMMENT ON COLUMN "group".description IS '用户组简介';
COMMENT ON COLUMN "group".created_time IS '创建时间';
COMMENT ON COLUMN "group".updated_time IS '更新时间';
COMMENT ON COLUMN "group".created_by IS '创建人';
COMMENT ON COLUMN "group".updated_by IS '更新人';

--岗位表
DROP TABLE IF EXISTS position;
CREATE TABLE position
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(200),
  description   VARCHAR(500),
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE position IS '岗位表';
COMMENT ON COLUMN position.id IS '岗位id';
COMMENT ON COLUMN position.name IS '岗位名称';
COMMENT ON COLUMN position.description IS '岗位简介';
COMMENT ON COLUMN position.created_time IS '创建时间';
COMMENT ON COLUMN position.updated_time IS '更新时间';
COMMENT ON COLUMN position.created_by IS '创建人';
COMMENT ON COLUMN position.updated_by IS '更新人';

--菜单表
DROP TABLE IF EXISTS menu;
CREATE TABLE menu
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
COMMENT ON TABLE menu IS '菜单表';
COMMENT ON COLUMN menu.id IS '菜单id';
COMMENT ON COLUMN menu.parent_id IS '父菜单id';
COMMENT ON COLUMN menu.name IS '菜单名称';
COMMENT ON COLUMN menu.type IS '菜单类型';
COMMENT ON COLUMN menu.href IS '菜单路径';
COMMENT ON COLUMN menu.icon IS '菜单图标';
COMMENT ON COLUMN menu.description IS '菜单简介';
COMMENT ON COLUMN menu.created_time IS '创建时间';
COMMENT ON COLUMN menu.updated_time IS '更新时间';
COMMENT ON COLUMN menu.created_by IS '创建人';
COMMENT ON COLUMN menu.updated_by IS '更新人';

--用户和组关系表
DROP TABLE IF EXISTS user_group_relation;
CREATE TABLE user_group_relation
(
  id           SERIAL PRIMARY KEY,
  user_id      INT          NOT NULL,
  group_id     INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE user_group_relation IS '用户和组关系表';
COMMENT ON COLUMN user_group_relation.id IS '关系id';
COMMENT ON COLUMN user_group_relation.user_id IS '用户id';
COMMENT ON COLUMN user_group_relation.group_id IS '用户组id';
COMMENT ON COLUMN user_group_relation.created_time IS '创建时间';
COMMENT ON COLUMN user_group_relation.updated_time IS '更新时间';
COMMENT ON COLUMN user_group_relation.created_by IS '创建人';
COMMENT ON COLUMN user_group_relation.updated_by IS '更新人';

--用户和角色系表
DROP TABLE IF EXISTS user_position_relation;
CREATE TABLE user_position_relation
(
  id           SERIAL PRIMARY KEY,
  user_id      INT          NOT NULL,
  position_id  INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE user_position_relation IS '用户和角色关系表';
COMMENT ON COLUMN user_position_relation.id IS '关系id';
COMMENT ON COLUMN user_position_relation.user_id IS '用户id';
COMMENT ON COLUMN user_position_relation.position_id IS '角色id';
COMMENT ON COLUMN user_position_relation.created_time IS '创建时间';
COMMENT ON COLUMN user_position_relation.updated_time IS '更新时间';
COMMENT ON COLUMN user_position_relation.created_by IS '创建人';
COMMENT ON COLUMN user_position_relation.updated_by IS '更新人';

--角色和菜单关系表
DROP TABLE IF EXISTS role_menu_relation;
CREATE TABLE role_menu_relation
(
  id           SERIAL PRIMARY KEY,
  menu_id      INT          NOT NULL,
  role_id      INT          NOT NULL,
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
COMMENT ON TABLE role_menu_relation IS '角色和菜单关系表';
COMMENT ON COLUMN role_menu_relation.id IS '关系id';
COMMENT ON COLUMN role_menu_relation.role_id IS '角色id';
COMMENT ON COLUMN role_menu_relation.menu_id IS '菜单id';
COMMENT ON COLUMN role_menu_relation.created_time IS '创建时间';
COMMENT ON COLUMN role_menu_relation.updated_time IS '更新时间';
COMMENT ON COLUMN role_menu_relation.created_by IS '创建人';
COMMENT ON COLUMN role_menu_relation.updated_by IS '更新人';

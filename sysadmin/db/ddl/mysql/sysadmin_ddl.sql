USE sc_admin;
-- 用户组表
DROP TABLE IF EXISTS groups;
CREATE TABLE groups
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  parent_id    VARCHAR(20)  NOT NULL COMMENT '用户组父id',
  name         VARCHAR(200) COMMENT '用户组名称',
  description  VARCHAR(500) COMMENT '描述',
  deleted      VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户组表';

-- 岗位表
DROP TABLE IF EXISTS position;
CREATE TABLE position
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  name         VARCHAR(200) COMMENT '岗位名称',
  description  VARCHAR(500) COMMENT '描述',
  deleted      VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '岗位表';

-- 菜单表
DROP TABLE IF EXISTS menu;
CREATE TABLE menu
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  parent_id    VARCHAR(20)  NOT NULL COMMENT '父菜单id',
  type         VARCHAR(100) COMMENT '菜单类型',
  href         VARCHAR(200) COMMENT '菜单路径',
  icon         VARCHAR(200) COMMENT '菜单图标',
  name         VARCHAR(200) COMMENT '菜单名称',
  description  VARCHAR(500) COMMENT '描述',
  order_num    INTEGER COMMENT '创建时间',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '菜单表';


-- 用户和组关系表
DROP TABLE IF EXISTS user_group_relation;
CREATE TABLE user_group_relation
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  user_id      VARCHAR(20)  NOT NULL COMMENT '用户id',
  group_id     VARCHAR(20)  NOT NULL COMMENT '用户组id',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和组关系表';


-- 用户和岗位系表
DROP TABLE IF EXISTS user_position_relation;
CREATE TABLE user_position_relation
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  user_id      VARCHAR(20)  NOT NULL COMMENT '用户id',
  position_id  VARCHAR(20)  NOT NULL COMMENT '角色id',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和岗位关系表';


-- 角色和菜单关系表
DROP TABLE IF EXISTS role_menu_relation;
CREATE TABLE role_menu_relation
(
  id           VARCHAR(20) PRIMARY KEY COMMENT 'id',
  menu_id      VARCHAR(20)  NOT NULL COMMENT '菜单id',
  role_id      VARCHAR(20)  NOT NULL COMMENT '角色id',
  created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
  created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
  updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色和菜单关系表';
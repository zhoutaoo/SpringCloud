SET NAMES utf8;

DROP DATABASE IF EXISTS sc_admin;
CREATE DATABASE sc_admin DEFAULT CHARSET utf8mb4;
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

--  用户表
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id                      VARCHAR(20) PRIMARY KEY COMMENT '用户id',
    username                VARCHAR(100) NOT NULL COMMENT '用户名',
    password                VARCHAR(100) NOT NULL COMMENT '用户密码密文',
    name                    VARCHAR(200) COMMENT '用户姓名',
    mobile                  VARCHAR(20) COMMENT '用户手机',
    description             VARCHAR(500) COMMENT '简介',
    deleted                 VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
    enabled                 BOOLEAN COMMENT '是否有效用户',
    account_non_expired     BOOLEAN COMMENT '账号是否未过期',
    credentials_non_expired BOOLEAN COMMENT '密码是否未过期',
    account_non_locked      BOOLEAN COMMENT '是否未锁定',
    created_time            DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time            DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by              VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by              VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户表';
CREATE UNIQUE INDEX ux_users_username
    ON users (username);
CREATE UNIQUE INDEX ux_users_mobile
    ON users (mobile);

--  角色表
DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id           VARCHAR(20) PRIMARY KEY COMMENT '角色id',
    code         VARCHAR(100) NOT NULL COMMENT '角色code',
    name         VARCHAR(200) COMMENT '角色名称',
    description  VARCHAR(500) COMMENT '简介',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色表';

-- 资源表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
    id           VARCHAR(20) PRIMARY KEY COMMENT '资源id',
    code         VARCHAR(100) NOT NULL COMMENT '资源code',
    type         VARCHAR(100) NOT NULL COMMENT '资源类型',
    name         VARCHAR(200) NOT NULL COMMENT '资源名称',
    url          VARCHAR(200) NOT NULL COMMENT '资源url',
    method       VARCHAR(20)  NOT NULL COMMENT '资源方法',
    description  VARCHAR(500) COMMENT '简介',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '资源表';
CREATE UNIQUE INDEX ux_resource_code
    ON resource (code);

-- 用户和角色关系表
DROP TABLE IF EXISTS user_role_relation;
CREATE TABLE user_role_relation
(
    id           VARCHAR(20) PRIMARY KEY COMMENT '关系id',
    user_id      VARCHAR(20)  NOT NULL COMMENT '用户id',
    role_id      VARCHAR(20)  NOT NULL COMMENT '角色id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和角色关系表';

-- 角色和资源关系表
DROP TABLE IF EXISTS role_resource_relation;
CREATE TABLE role_resource_relation
(
    id           VARCHAR(20) PRIMARY KEY COMMENT '关系id',
    resource_id  VARCHAR(20)  NOT NULL COMMENT '角色id',
    role_id      VARCHAR(20)  NOT NULL COMMENT '资源id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色和资源关系表';

-- DML准备初始化数据

-- 用户
INSERT INTO users (id, username, password, deleted, enabled, account_non_expired, credentials_non_expired, account_non_locked, name, mobile, created_time, updated_time, created_by, updated_by)
VALUES
(101, 'admin', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', 'N', true, true, true, true,
 '超级管理员', '', now(), now(), 'system', 'system'),
(102, 'zhoutaoo', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', 'N', true, true, true, true,
 '周涛', 15619841000, now(), now(), 'system', 'system');
-- 角色
INSERT INTO roles (id, code, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, 'ADMIN', '超级管理员', '公司IT总负责人', now(), now(), 'system', 'system'),
       (102, 'FIN', '财务', '财务', now(), now(), 'system', 'system'),
       (103, 'IT', 'IT', 'IT角色', now(), now(), 'system', 'system');
-- 资源
INSERT INTO resource (id, name, code, type, url, method, description, created_time, updated_time, created_by, updated_by)
VALUES (101, '新增用户', 'user_manager:btn_add', 'button', '/user', 'POST', '新增用户功能', now(), now(), 'system', 'system'),
       (102, '编辑用户', 'user_manager:btn_edit', 'button', '/user/{id}', 'PUT', '编辑用户功能', now(), now(), 'system', 'system'),
       (103, '删除用户', 'user_manager:btn_del', 'button', '/user/{id}', 'DELETE', '根据用户id删除用户', now(), now(), 'system', 'system'),
       (104, '查看用户', 'user_manager:view', 'url', '/user/{id}', 'GET', '根据用户id获取用户', now(), now(), 'system', 'system'),
       (105, '搜索用户', 'user_manager:query', 'url', '/user/conditions', 'POST', '根据条件查询用户', now(), now(), 'system', 'system'),
       (106, '获取用户', 'user_manager:get', 'url', '/user', 'GET', '根据唯一标识获取用户', now(), now(), 'system', 'system'),
       (201, '新增角色', 'role_manager:btn_add', 'url', '/role', 'POST', '新增角色功能', now(), now(), 'system', 'system'),
       (202, '编辑角色', 'role_manager:btn_edit', 'url', '/role/{id}', 'PUT', '编辑角色功能', now(), now(), 'system', 'system'),
       (203, '删除角色', 'role_manager:btn_del', 'url', '/role/{id}', 'DELETE', '根据id删除角色', now(), now(), 'system', 'system'),
       (204, '查看角色', 'role_manager:view', 'url', '/role/{id}', 'GET', '根据id获取角色', now(), now(), 'system', 'system'),
       (205, '根据用户id查询角色', 'role_manager:user', 'url', '/role/user/{userId}', 'GET', '根据用户id获取用户所拥有的角色集', now(), now(), 'system', 'system'),
       (206, '获取所有角色', 'role_manager:all', 'url', '/role/all', 'GET', '获取所有角色', now(), now(), 'system', 'system'),
       (207, '搜索角色', 'role_manager:query', 'url', '/role/conditions', 'POST', '根据条件查询角色', now(), now(), 'system', 'system'),
       (301, '根据父id查询组', 'group_manager:parent', 'url', '/group/parent/{id}', 'GET', '根据父id查询用户组', now(), now(), 'system', 'system'),
       (302, '查看用户组', 'group_manager:get', 'url', '/group/{id}', 'GET', '根据id查询用户组', now(), now(), 'system', 'system'),
       (303, '搜索用户组', 'group_manager:query', 'url', '/group/conditions', 'POST', '根据条件查询用户组信息', now(), now(), 'system', 'system'),
       (304, '删除用户组', 'group_manager:del', 'url', '/group/{id}', 'DELETE', '根据用户id删除用户组', now(), now(), 'system', 'system'),
       (305, '编辑用户组', 'group_manager:edit', 'url', '/group/{id}', 'PUT', '修改用户组', now(), now(), 'system', 'system'),
       (306, '新增用户组', 'group_manager:add', 'url', '/group', 'POST', '新增用户组', now(), now(), 'system', 'system');

-- 用户关系授权
INSERT INTO user_role_relation (id, user_id, role_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
       (102, 102, 101, now(), now(), 'system', 'system'),
       (103, 102, 103, now(), now(), 'system', 'system');
-- 角色与资源关系表
INSERT INTO role_resource_relation (id, role_id, resource_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
       (102, 101, 102, now(), now(), 'system', 'system'),
       (103, 101, 103, now(), now(), 'system', 'system'),
       (104, 101, 104, now(), now(), 'system', 'system'),
       (105, 101, 105, now(), now(), 'system', 'system'),
       (106, 101, 106, now(), now(), 'system', 'system'),
       (201, 101, 201, now(), now(), 'system', 'system'),
       (202, 101, 202, now(), now(), 'system', 'system'),
       (203, 101, 203, now(), now(), 'system', 'system'),
       (204, 101, 204, now(), now(), 'system', 'system'),
       (205, 101, 205, now(), now(), 'system', 'system'),
       (206, 101, 206, now(), now(), 'system', 'system'),
       (207, 101, 207, now(), now(), 'system', 'system'),
       (208, 101, 301, now(), now(), 'system', 'system'),
       (209, 101, 302, now(), now(), 'system', 'system'),
       (210, 101, 303, now(), now(), 'system', 'system'),
       (211, 101, 304, now(), now(), 'system', 'system'),
       (212, 101, 305, now(), now(), 'system', 'system'),
       (213, 101, 306, now(), now(), 'system', 'system'),
       (301, 103, 101, now(), now(), 'system', 'system'),
       (302, 103, 102, now(), now(), 'system', 'system'),
       (303, 103, 103, now(), now(), 'system', 'system'),
       (304, 103, 104, now(), now(), 'system', 'system'),
       (305, 103, 105, now(), now(), 'system', 'system'),
       (306, 103, 106, now(), now(), 'system', 'system'),
       (310, 103, 201, now(), now(), 'system', 'system'),
       (311, 103, 202, now(), now(), 'system', 'system'),
       (312, 103, 203, now(), now(), 'system', 'system'),
       (313, 103, 204, now(), now(), 'system', 'system'),
       (314, 103, 205, now(), now(), 'system', 'system'),
       (315, 103, 206, now(), now(), 'system', 'system'),
       (316, 103, 207, now(), now(), 'system', 'system'),
       (317, 103, 301, now(), now(), 'system', 'system'),
       (318, 103, 302, now(), now(), 'system', 'system'),
       (319, 103, 303, now(), now(), 'system', 'system'),
       (320, 103, 304, now(), now(), 'system', 'system'),
       (321, 103, 305, now(), now(), 'system', 'system'),
       (322, 103, 306, now(), now(), 'system', 'system');

-- 岗位
INSERT INTO position (id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, '首席执行官', '公司CEO，负责公司整体运转', now(), now(), 'system', 'system'),
       (102, '首席运营官', '公司COO，负责公司整体运营', now(), now(), 'system', 'system'),
       (103, '首席技术官', '公司CTO，负责公司整体运营', now(), now(), 'system', 'system');
-- 用户组
INSERT INTO groups (id, parent_id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, -1, '总公司', '总公司', now(), now(), 'system', 'system'),
       (102, 101, '上海分公司', '上海分公司', now(), now(), 'system', 'system'),
       (103, 102, '研发部门', '负责产品研发', now(), now(), 'system', 'system'),
       (104, 102, '产品部门', '负责产品设计', now(), now(), 'system', 'system'),
       (105, 102, '运营部门', '负责公司产品运营', now(), now(), 'system', 'system'),
       (106, 102, '销售部门', '负责公司产品销售', now(), now(), 'system', 'system'),
       (107, 101, '北京分公司', '北京分公司', now(), now(), 'system', 'system');
-- 菜单
INSERT INTO menu (id, parent_id, type, href, icon, name, description, order_num, created_time, updated_time, created_by, updated_by)
VALUES (101, -1, 'MENU', '/admin', 'setting', '系统管理', '系统设置管理', 0, now(), now(), 'system', 'system'),
       (102, 101, 'MENU', '/admin/users', 'fa-user', '用户管理', '用户新增，修改，查看，删除', 10, now(), now(), 'system', 'system'),
       (103, 101, 'MENU', '/admin/menus', 'category', '菜单管理', '菜单新增，修改，删除', 20, now(), now(), 'system', 'system');

INSERT INTO user_group_relation (id, user_id, group_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
       (102, 102, 101, now(), now(), 'system', 'system');
INSERT INTO user_position_relation (id, user_id, position_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 103, now(), now(), 'system', 'system'),
       (102, 102, 103, now(), now(), 'system', 'system');
-- 角色关系表
INSERT INTO role_menu_relation (id, role_id, menu_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
       (102, 101, 102, now(), now(), 'system', 'system'),
       (103, 101, 103, now(), now(), 'system', 'system'),
       (104, 102, 101, now(), now(), 'system', 'system'),
       (105, 102, 102, now(), now(), 'system', 'system'),
       (106, 103, 101, now(), now(), 'system', 'system'),
       (107, 103, 102, now(), now(), 'system', 'system'),
       (108, 103, 103, now(), now(), 'system', 'system');

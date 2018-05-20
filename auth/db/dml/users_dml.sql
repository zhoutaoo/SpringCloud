--用户
INSERT INTO users (id, username, password, enabled, account_non_expired, credentials_non_expired, account_non_locked, name, mobile, created_time, updated_time, created_by, updated_by)
VALUES
  (101, 'admin', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', 'true', 'true', 'true', 'true',
        '超级管理员', '', now(), now(), 'system', 'system'),
  (102, 'zhoutaoo', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', 'true', 'true', 'true', 'true',
        '周涛', 15619841000, now(), now(), 'system', 'system');
--岗位
INSERT INTO positions (id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, '首席执行官', '公司CEO，负责公司整体运转', now(), now(), 'system', 'system'),
  (102, '首席运营官', '公司COO，负责公司整体运营', now(), now(), 'system', 'system'),
  (103, '首席技术官', '公司CTO，负责公司整体运营', now(), now(), 'system', 'system');
--角色
INSERT INTO roles (id, code, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, 'ADMIN', '超级管理员', '公司IT总负责人', now(), now(), 'system', 'system'),
  (102, 'FIN', '财务', '财务', now(), now(), 'system', 'system'),
  (103, 'IT', 'IT', 'IT角色', now(), now(), 'system', 'system');
--用户组
INSERT INTO groups (id, parent_id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, -1, '总公司', '总公司', now(), now(), 'system', 'system'),
  (102, 101, '上海分公司', '上海分公司', now(), now(), 'system', 'system'),
  (103, 102, '研发部门', '负责产品研发', now(), now(), 'system', 'system'),
  (104, 102, '产品部门', '负责产品设计', now(), now(), 'system', 'system'),
  (105, 102, '运营部门', '负责公司产品运营', now(), now(), 'system', 'system'),
  (106, 102, '销售部门', '负责公司产品销售', now(), now(), 'system', 'system'),
  (107, 101, '北京分公司', '北京分公司', now(), now(), 'system', 'system');
--菜单
INSERT INTO menus (id, parent_id, type, href, icon, name, description, order_num, created_time, updated_time, created_by, updated_by)
VALUES (101, -1, 'MENU', '/admin', 'setting', '系统管理', '系统设置管理', 0, now(), now(), 'system', 'system'),
  (102, 101, 'MENU', '/admin/users', 'fa-user', '用户管理', '用户新增，修改，查看，删除', 10, now(), now(), 'system', 'system'),
  (103, 101, 'MENU', '/admin/menus', 'category', '菜单管理', '菜单新增，修改，删除', 20, now(), now(), 'system', 'system');
--资源
INSERT INTO resources (id, name, code, type, url, method, description, created_time, updated_time, created_by, updated_by)
VALUES (101, '新增', 'user_manager:btn_add', 'button', '/users', 'POST', '新增用户功能', now(), now(), 'system', 'system'),
  (102, '编辑', 'user_manager:btn_edit', 'button', '/users', 'PUT', '编辑用户功能', now(), now(), 'system', 'system'),
  (103, '删除', 'user_manager:btn_del', 'button', '/users/{id}', 'DELETE', '删除用户功能', now(), now(), 'system', 'system'),
  (104, '查看', 'user_manager:view', 'url', '/users/{id}', 'GET', '查询用户功能', now(), now(), 'system', 'system');

--用户关系授权
INSERT INTO users_roles_relation (id, user_id, role_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
  (102, 102, 101, now(), now(), 'system', 'system'),
  (103, 102, 103, now(), now(), 'system', 'system');
INSERT INTO users_groups_relation (id, user_id, group_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
  (102, 102, 101, now(), now(), 'system', 'system');
INSERT INTO users_positions_relation (id, user_id, position_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 103, now(), now(), 'system', 'system'),
  (102, 102, 103, now(), now(), 'system', 'system');
--角色关系表
INSERT INTO roles_menus_relation (id, role_id, menu_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
  (102, 101, 102, now(), now(), 'system', 'system'),
  (103, 101, 103, now(), now(), 'system', 'system'),
  (104, 102, 101, now(), now(), 'system', 'system'),
  (105, 102, 102, now(), now(), 'system', 'system'),
  (106, 103, 101, now(), now(), 'system', 'system'),
  (107, 103, 102, now(), now(), 'system', 'system'),
  (108, 103, 103, now(), now(), 'system', 'system');
INSERT INTO roles_resources_relation (id, role_id, resource_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
  (102, 101, 102, now(), now(), 'system', 'system'),
  (103, 101, 103, now(), now(), 'system', 'system'),
  (104, 101, 104, now(), now(), 'system', 'system'),
  (105, 102, 104, now(), now(), 'system', 'system'),
  (106, 103, 101, now(), now(), 'system', 'system'),
  (107, 103, 102, now(), now(), 'system', 'system'),
  (108, 103, 103, now(), now(), 'system', 'system'),
  (109, 103, 104, now(), now(), 'system', 'system');
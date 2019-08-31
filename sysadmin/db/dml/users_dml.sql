USE sc_admin;

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
  (207, '搜索角色', 'role_manager:query', 'url', '/role/conditions', 'POST', '根据条件查询角色', now(), now(), 'system', 'system');

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
  (105, 102, 104, now(), now(), 'system', 'system'),
  (106, 103, 101, now(), now(), 'system', 'system'),
  (107, 103, 102, now(), now(), 'system', 'system'),
  (108, 103, 103, now(), now(), 'system', 'system'),
  (109, 103, 104, now(), now(), 'system', 'system'),
  (110, 101, 105, now(), now(), 'system', 'system'),
  (111, 103, 105, now(), now(), 'system', 'system'),
  (112, 101, 106, now(), now(), 'system', 'system'),
  (113, 103, 106, now(), now(), 'system', 'system'),
  (201, 101, 201, now(), now(), 'system', 'system'),
  (202, 101, 202, now(), now(), 'system', 'system'),
  (203, 101, 203, now(), now(), 'system', 'system'),
  (204, 101, 204, now(), now(), 'system', 'system'),
  (205, 101, 205, now(), now(), 'system', 'system'),
  (206, 101, 206, now(), now(), 'system', 'system'),
  (207, 101, 207, now(), now(), 'system', 'system'),
  (210, 103, 204, now(), now(), 'system', 'system'),
  (211, 103, 205, now(), now(), 'system', 'system'),
  (212, 103, 207, now(), now(), 'system', 'system'),
  (213, 103, 203, now(), now(), 'system', 'system'),
  (214, 103, 201, now(), now(), 'system', 'system'),
  (215, 103, 202, now(), now(), 'system', 'system'),
  (216, 103, 206, now(), now(), 'system', 'system');

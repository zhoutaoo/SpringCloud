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

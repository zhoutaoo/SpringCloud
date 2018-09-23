--岗位
INSERT INTO position (id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, '首席执行官', '公司CEO，负责公司整体运转', now(), now(), 'system', 'system'),
  (102, '首席运营官', '公司COO，负责公司整体运营', now(), now(), 'system', 'system'),
  (103, '首席技术官', '公司CTO，负责公司整体运营', now(), now(), 'system', 'system');
--用户组
INSERT INTO "group" (id, parent_id, name, description, created_time, updated_time, created_by, updated_by)
VALUES (101, -1, '总公司', '总公司', now(), now(), 'system', 'system'),
  (102, 101, '上海分公司', '上海分公司', now(), now(), 'system', 'system'),
  (103, 102, '研发部门', '负责产品研发', now(), now(), 'system', 'system'),
  (104, 102, '产品部门', '负责产品设计', now(), now(), 'system', 'system'),
  (105, 102, '运营部门', '负责公司产品运营', now(), now(), 'system', 'system'),
  (106, 102, '销售部门', '负责公司产品销售', now(), now(), 'system', 'system'),
  (107, 101, '北京分公司', '北京分公司', now(), now(), 'system', 'system');
--菜单
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
--角色关系表
INSERT INTO role_menu_relation (id, role_id, menu_id, created_time, updated_time, created_by, updated_by)
VALUES (101, 101, 101, now(), now(), 'system', 'system'),
  (102, 101, 102, now(), now(), 'system', 'system'),
  (103, 101, 103, now(), now(), 'system', 'system'),
  (104, 102, 101, now(), now(), 'system', 'system'),
  (105, 102, 102, now(), now(), 'system', 'system'),
  (106, 103, 101, now(), now(), 'system', 'system'),
  (107, 103, 102, now(), now(), 'system', 'system'),
  (108, 103, 103, now(), now(), 'system', 'system');

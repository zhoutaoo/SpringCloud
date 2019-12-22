-- Create Database
-- ----------------------------------------------------------
CREATE DATABASE IF NOT EXISTS moss DEFAULT CHARACTER SET = utf8mb4;

Use moss;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_app`
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app`
(
    `id`                      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用Id',
    `app_id`                  varchar(200)        NOT NULL,
    `name`                    varchar(200)        NOT NULL DEFAULT '' COMMENT '应用名称',
    `owner_name`              varchar(200)        NOT NULL DEFAULT '' COMMENT '负责人姓名',
    `owner_id`                varchar(200)                 DEFAULT '0' COMMENT '负责人Id',
    `project_name`            varchar(200)        NOT NULL DEFAULT '' COMMENT '所属项目名称',
    `project_key`             varchar(200)        NOT NULL DEFAULT '0' COMMENT '所属项目Id',
    `description`             varchar(1000)                DEFAULT '' COMMENT '应用描述',
    `gmt_create`              timestamp           NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified`            timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`              tinyint(4)          NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    `status`                  tinyint(4)                   DEFAULT '0' COMMENT '应用的状态 0-创建 1-开发中 2-运行中 3-已下线',
    `take_over`               tinyint(4)                   DEFAULT '0',
    `ops_owner_name`          varchar(200)                 DEFAULT NULL,
    `ops_owner_id`            varchar(200)                 DEFAULT NULL,
    `repo_url`                varchar(1000)                DEFAULT NULL,
    `bu_name`                 varchar(255)                 DEFAULT NULL,
    `spring_application_name` varchar(255)                 DEFAULT NULL,
    `spring_boot_version`     int(2)                       DEFAULT '0',
    `spring_cloud_version`    int(2)                       DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 287
  DEFAULT CHARSET = utf8 COMMENT ='应用表';

-- ----------------------------
--  Records of `t_app`
-- ----------------------------
BEGIN;
INSERT INTO `t_app`
VALUES ('1', 'halo-moss', 'halo-moss', '许进', 'xujin', 'halo', 'Halo', '', '2019-02-27 02:23:42', '2019-04-21 10:15:04',
        '0', '2', '1', '韩令三', 'lingshan.han', 'https://github.com/SoftwareKing/Moss.git', 'XX', 'halo-moss', '2', '2'),
       ('2', 'moss-sample-1.5.x', 'moss-sample-1.5.x', '杜为极', 'weiji.du', 'BKCASHIER', 'MOSS', '',
        '2019-02-27 02:23:42', '2019-04-21 10:15:27', '0', '2', '1', '叶张', 'dingf.ye001',
        'https://github.com/SoftwareKing/Moss.git', '金融XX', 'moss-sample-1.5.x', '1', '1'),
       ('3', 'moss-sample-2.1.x', 'moss-sample-2.1.x', '许进', 'guojian.li', 'MOSS', 'MOSS', '', '2019-02-27 02:23:42',
        '2019-04-21 10:15:22', '0', '2', '1', '齐思宇', 'daiying.qi', 'https://github.com/SoftwareKing/Moss.git', '房XX',
        'moss-sample-2.1.x', '2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `t_app_name`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_name`;
CREATE TABLE `t_app_name`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`         varchar(200)        NOT NULL DEFAULT '' COMMENT '应用名称',
    `gmt_create`   timestamp           NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)          NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8 COMMENT ='应用name表';

-- ----------------------------
--  Table structure for `t_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_data`;
CREATE TABLE `t_dict_data`
(
    `id`           int(11)                        NOT NULL AUTO_INCREMENT COMMENT '数据字典详细主键',
    `dict_code`    varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '数据字典分类标识',
    `item_name`    varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '数据字典详细名称',
    `item_value`   varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '数据字典详细值',
    `item_desc`    varchar(64) CHARACTER SET utf8          DEFAULT NULL COMMENT '数据字典详细描述',
    `item_sort`    int(16)                                 DEFAULT NULL COMMENT '排序',
    `gmt_create`   timestamp                      NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)                     NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    `status`       tinyint(4)                              DEFAULT '0' COMMENT 'COMMENT ''数据字典项启用状态，1：启用，0：未启用'',',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='数据字典详细表';

-- ----------------------------
--  Records of `t_dict_data`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict_data`
VALUES ('1', 'springBootVersion', '1.5.13.RELEASE', '1', '1.5.13.RELEASE', '0', '1970-01-02 00:00:00',
        '2019-03-15 17:44:15', '0', '1'),
       ('2', 'springBootVersion', '2.0.7.RELEASE', '2', '2.0.7.RELEASE', '0', '1970-01-02 00:00:00',
        '2019-03-15 17:44:19', '0', '1'),
       ('3', 'springCloudVersion', 'Edgware.SR3', '1', 'Spring Cloud Edgware.SR3', '0', '1970-01-02 00:00:00',
        '2019-03-15 17:44:27', '0', '1'),
       ('4', 'springCloudVersion', 'Finchley.SR2', '2', 'Spring Cloud Finchley.SR2', '0', '1970-01-02 00:00:00',
        '2019-03-15 17:44:31', '0', '1'),
       ('5', 'frameworkVerison', '1.1.8.RELEASE', '1', '1.1.8.RELEASE', '0', '1970-01-02 00:00:00',
        '2019-04-10 19:58:48', '1', '1'),
       ('6', 'frameworkVerison', '2.0.0.RELEASE', '2', '2.0.0.RELEASE', '0', '1970-01-02 00:00:00',
        '2019-04-10 19:58:48', '1', '1'),
       ('11', 'appFlickerRule', '当实例数小于2', '1', '当实例数小于2时闪烁', '0', '1970-01-02 00:00:00', '2019-03-19 13:44:36', '0',
        '1'),
       ('12', 'scoringRules', '当实例数大于1', '1', '当实例数大于1给1颗星', '0', '1970-01-02 00:00:00', '2019-03-19 13:42:48', '0',
        '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_type`;
CREATE TABLE `t_dict_type`
(
    `id`           int(11)                        NOT NULL AUTO_INCREMENT COMMENT '数据字典分类主键',
    `dict_name`    varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '数据字典分类名称',
    `dict_code`    varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '数据字典分类唯一标识',
    `status`       tinyint(4)                     NOT NULL DEFAULT '0' COMMENT '数据字典分类启用状态，0：启用，1：未启用',
    `gmt_create`   timestamp                      NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)                     NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='数据字典分类表';

-- ----------------------------
--  Records of `t_dict_type`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict_type`
VALUES ('1', 'Spring Boot的版本', 'springBootVersion', '0', '2019-03-12 02:36:02', '2019-03-14 14:17:10', '0'),
       ('2', 'Spring Cloud的版本', 'springCloudVersion', '0', '2019-03-12 02:36:42', '2019-03-14 14:17:27', '0'),
       ('3', 'Summer Framework的版本', 'frameworkVerison', '0', '2019-03-12 02:37:21', '2019-03-14 14:17:41', '1'),
       ('6', 'App根据实例数闪烁规则', 'appFlickerRule', '0', '2019-03-15 01:55:56', '2019-03-15 01:55:56', '0'),
       ('7', '评分规则', 'scoringRules', '0', '2019-03-15 01:56:22', '2019-03-15 01:56:22', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
    `parent_id`    bigint(20)            DEFAULT NULL,
    `name`         varchar(255)          DEFAULT NULL,
    `parent_ids`   varchar(255)          DEFAULT NULL,
    `url`          varchar(255)          DEFAULT NULL,
    `roles`        varchar(255)          DEFAULT NULL,
    `sort`         int(11)      NOT NULL DEFAULT '1',
    `icon`         varchar(255)          DEFAULT NULL,
    `gmt_create`   timestamp    NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)   NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    `key`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `t_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_menu`
VALUES ('1', '0', '总览', '0', '/dashboard', 'USER,ADMIN', '6', 'dashboard', '1970-01-02 00:00:00', '2019-03-01 18:36:14',
        '0', 'dashboard'),
       ('2', '0', '服务治理', '0', '/#/application', 'USER,ADMIN', '5', 'appstore', '1970-01-02 00:00:00',
        '2019-03-01 17:34:03', '0', 'serviceMgmt'),
       ('5', '0', '平台管理', '0', '/#', 'USER,ADMIN', '1', 'hdd', '1970-01-02 00:00:00', '2019-04-04 04:12:52', '0',
        'projectMgmt'),
       ('6', '4', '推送记录', '0', '/switchCenter', 'USER,ADMIN', '1', 'code', '1970-01-02 00:00:00', '2019-01-03 16:21:07',
        '0', 'switchPushLog'),
       ('10', '4', '开关管理', '0', '/switchCenter', 'USER,ADMIN', '1', 'key', '1970-01-02 00:00:00', '2019-01-03 11:01:32',
        '0', 'switchManage'),
       ('11', '4', '机器管理', '0', null, 'USER,ADMIN', '1', 'database', '1970-01-02 00:00:00', '2018-12-10 14:04:18', '0',
        'machine'),
       ('12', '0', '系统管理', '0', '', 'ADMIN', '1', 'setting', '1970-01-02 00:00:00', '2018-12-10 14:15:55', '0', 'sys'),
       ('13', '12', '菜单管理', '0', '/OSManage', 'USER,ADMIN', '1', 'profile', '1970-01-02 00:00:00',
        '2018-12-10 14:04:24', '0', 'menuManage'),
       ('16', '5', '应用管理', '0', '/application', 'ADMIN', '1', 'euro', '1970-01-02 00:00:00', '2019-04-19 21:47:35', '0',
        'appAccept'),
       ('24', '2', '服务管理', '0', '/application', 'USER,ADMIN', '5', 'deployment-unit', '2019-02-22 03:21:10',
        '2019-04-19 21:49:06', '0', 'serviceManage'),
       ('25', '2', '事件中心', '0', '/application', 'USER,ADMIN', '0', 'setting', '2019-02-25 03:22:10',
        '2019-02-25 03:22:24', '0', 'eventLog'),
       ('26', '2', '实例管理', '0', '/application', 'USER,ADMIN', '3', 'ant-design', '1970-01-02 00:00:00',
        '2019-04-19 21:50:22', '0', 'list'),
       ('27', '5', '项目列表', '0', '/application', 'ADMIN', '2', 'project', '2019-03-07 02:33:31', '2019-04-19 21:48:32',
        '0', 'Project'),
       ('28', '12', '用户列表', '0', '/userMgmt', 'USER,ADMIN', '0', 'user', '2019-03-07 02:35:53', '2019-04-04 06:53:52',
        '0', 'userMgmt'),
       ('30', '12', '数据字典', '0', '/remoteConfig', 'ADMIN', '0', 'appstore', '2019-03-13 03:10:28',
        '2019-03-13 03:10:28', '0', 'remoteConfig'),
       ('31', '5', '注册中心', '0', '/registerCenterMgmt', 'ADMIN', '0', 'gateway', '2019-04-04 04:13:20',
        '2019-04-19 21:48:02', '0', 'registerCenterMgmt');
COMMIT;

-- ----------------------------
--  Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目Id',
    `name`         varchar(200)        NOT NULL DEFAULT '' COMMENT '项目名称',
    `cname`        varchar(200)        NOT NULL DEFAULT '' COMMENT '项目中文名称',
    `key`          varchar(200)        NOT NULL DEFAULT '0' COMMENT '项目key',
    `owner_name`   varchar(200)        NOT NULL DEFAULT '' COMMENT '负责人姓名',
    `owner_id`     varchar(200)                 DEFAULT '0' COMMENT '负责人Id',
    `description`  varchar(1000)                DEFAULT '' COMMENT '项目描述',
    `gmt_create`   timestamp           NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)          NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 216
  DEFAULT CHARSET = utf8 COMMENT ='项目表';

-- ----------------------------
--  Records of `t_project`
-- ----------------------------
BEGIN;
INSERT INTO `t_project`
VALUES ('1', '服务治理', '服务治理', 'MOSS', '许进', 'xujin', '服务治理', '2019-02-28 21:01:18', '2019-04-05 14:42:36', '0'),
       ('2', '中台项目', '中台项目', 'HALO', '许进', 'xujin', '', '2019-02-28 21:02:25', '2019-04-05 14:43:09', '0'),
       ('3', 'PMO', 'PMO', 'PMO', '黎茂', 'mao.li001', '', '2019-02-28 21:02:25', '2019-03-07 16:45:13', '1'),
       ('4', 'XXXUED', 'XXXUED', 'UED', '王佳琳', 'jialin.wang', '', '2019-02-28 21:02:26', '2019-03-07 16:45:17', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_register_center`
-- ----------------------------
DROP TABLE IF EXISTS `t_register_center`;
CREATE TABLE `t_register_center`
(
    `id`           int(11)                       NOT NULL AUTO_INCREMENT,
    `code`         varchar(50) COLLATE utf8_bin  NOT NULL COMMENT '注册中心标识',
    `url`          varchar(100) COLLATE utf8_bin NOT NULL COMMENT '注册中心url',
    `desc`         varchar(64) CHARACTER SET utf8         DEFAULT NULL COMMENT '描述',
    `status`       tinyint(1)                    NOT NULL COMMENT '1 运行,0 停用',
    `gmt_create`   timestamp                     NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)                    NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    `name`         varchar(50) COLLATE utf8_bin           DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='注册中心表';

-- ----------------------------
--  Records of `t_register_center`
-- ----------------------------
BEGIN;
INSERT INTO `t_register_center`
VALUES ('1', 'sq', 'http://eureka.springcloud.cn/eureka/', '社区注册', '1', '2019-04-04 04:28:11', '2019-04-05 18:54:45',
        '0', '社区注册'),
       ('4', 'zj', 'http://localhost:8071/eureka', '自己本地', '0', '2019-04-20 22:37:04', '2019-04-21 11:37:25', '0',
        '自己本地');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`           int(11)                      NOT NULL AUTO_INCREMENT,
    `username`     varchar(50) COLLATE utf8_bin NOT NULL COMMENT '英文名唯一',
    `name`         varchar(50) COLLATE utf8_bin NOT NULL COMMENT '中文名',
    `password`     varchar(50) COLLATE utf8_bin NOT NULL COMMENT '密码',
    `status`       tinyint(1)                   NOT NULL COMMENT '是否启用',
    `gmt_create`   timestamp                    NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)                   NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    `email`        varchar(255) COLLATE utf8_bin         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户表';

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user`
VALUES ('1', 'xujin', 'xujin', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2019-03-06 20:23:50', '2019-03-08 19:15:35', '0',
        'Software_King@qq.com'),
       ('6', 'admin', 'admin', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2019-04-12 04:37:00', '2019-04-21 11:38:48', '0',
        'admin@qq.com');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_app`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_app`;
CREATE TABLE `t_user_app`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `mail_nick_name` varchar(200)        NOT NULL DEFAULT '' COMMENT '邮箱前缀名称',
    `app_id`         varchar(200)        NOT NULL DEFAULT '' COMMENT '应用名称',
    `gmt_create`     timestamp           NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     tinyint(4)          NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8 COMMENT ='应用name表';

-- ----------------------------
--  Records of `t_user_app`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_app`
VALUES ('38', 'xujin', 'halo-moss', '2019-03-10 05:46:39', '2019-04-05 01:31:23', '1'),
       ('39', 'xujin', 'loop_test', '2019-03-10 07:44:23', '2019-03-10 07:44:27', '1'),
       ('40', 'admin', 'halo-moss', '2019-03-11 22:45:05', '2019-03-11 22:45:07', '1'),
       ('41', 'test', 'halo-moss', '2019-03-12 00:03:39', '2019-03-12 00:03:51', '1'),
       ('42', 'test', 'loop_test', '2019-03-12 00:52:27', '2019-03-12 01:04:15', '1'),
       ('43', 'admin', 'loop_test', '2019-03-12 04:37:18', '2019-03-12 04:37:21', '1'),
       ('44', 'admin', 'loop_test', '2019-03-12 04:37:23', '2019-03-12 04:37:24', '1'),
       ('45', 'admin', 'loop_test', '2019-03-14 09:13:35', '2019-03-14 09:13:41', '1'),
       ('46', 'test', 'halo-moss', '2019-03-18 04:17:17', '2019-03-18 04:17:19', '1'),
       ('47', 'xujin', 'halo-moss', '2019-04-10 06:47:12', '2019-04-10 06:47:12', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles`
(
    `id`           int(11)                      NOT NULL AUTO_INCREMENT,
    `username`     varchar(50) COLLATE utf8_bin NOT NULL,
    `role`         varchar(50) COLLATE utf8_bin NOT NULL,
    `gmt_create`   timestamp                    NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
    `gmt_modified` timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(4)                   NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ix_auth_username` (`username`, `role`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
--  Records of `t_user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_roles`
VALUES ('11', 'admin', 'ADMIN', '2019-03-06 20:24:51', '2019-03-07 16:27:54', '0'),
       ('12', 'xujin', 'ADMIN', '2019-03-08 05:15:41', '2019-03-08 19:16:52', '0'),
       ('13', 'test', 'USER', '1970-01-02 00:00:00', '2019-03-12 12:52:40', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
--用户表
CREATE TABLE users
(
  id                      SERIAL,
  username                CHARACTER VARYING(100),
  password                CHARACTER VARYING(100),
  enabled                 BOOLEAN,
  account_non_expired     BOOLEAN,
  credentials_non_expired BOOLEAN,
  account_non_locked      BOOLEAN,
  name                    CHARACTER VARYING(256),
  moblie                  CHARACTER VARYING(20),
  created_time            TIMESTAMP              NOT NULL DEFAULT now(),
  updated_time            TIMESTAMP              NOT NULL DEFAULT now(),
  created_by              CHARACTER VARYING(100) NOT NULL,
  updated_by              CHARACTER VARYING(100) NOT NULL
);
SELECT setval('users_id_seq', 1000000);

CREATE UNIQUE INDEX ux_users_username
  ON users (username);
CREATE UNIQUE INDEX ux_users_mobile
  ON users (moblie);

COMMENT ON COLUMN users.id IS '用户id';
COMMENT ON COLUMN users.username IS '用户登陆名';
COMMENT ON COLUMN users.password IS '用户登陆名';
COMMENT ON COLUMN users.enabled IS '是否有效用户';
COMMENT ON COLUMN users.account_non_expired IS '账号是否未过期';
COMMENT ON COLUMN users.credentials_non_expired IS '密码是否未过期';
COMMENT ON COLUMN users.name IS '账号是否未锁定';
COMMENT ON COLUMN users.created_time IS '创建时间';
COMMENT ON COLUMN users.updated_time IS '更新时间';
COMMENT ON COLUMN users.created_by IS '创建人';
COMMENT ON COLUMN users.updated_by IS '更新人';


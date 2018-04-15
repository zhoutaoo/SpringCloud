--用户表
CREATE TABLE users
(
  id                      SERIAL PRIMARY KEY,
  username                VARCHAR(100),
  password                VARCHAR(100),
  name                    VARCHAR(256),
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
SELECT setval('users_id_seq', 1000000);

CREATE UNIQUE INDEX ux_users_username
  ON users (username);
CREATE UNIQUE INDEX ux_users_mobile
  ON users (mobile);

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


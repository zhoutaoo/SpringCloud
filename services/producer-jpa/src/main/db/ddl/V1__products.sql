DROP TABLE IF EXISTS products;
CREATE TABLE products
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(200)            NOT NULL,
  description  VARCHAR(500),
  created_time TIMESTAMP DEFAULT now() NOT NULL,
  updated_time TIMESTAMP DEFAULT now() NOT NULL,
  created_by   VARCHAR(100)            NOT NULL,
  updated_by   VARCHAR(100)            NOT NULL
);

COMMENT ON COLUMN products.id IS '编号';
COMMENT ON COLUMN products.name IS '产品名称';
COMMENT ON COLUMN products.description IS '产品描述';
COMMENT ON COLUMN products.created_time IS '创建时间';
COMMENT ON COLUMN products.updated_time IS '更新时间';
COMMENT ON COLUMN products.created_by IS '创建人';
COMMENT ON COLUMN products.updated_by IS '更新人';


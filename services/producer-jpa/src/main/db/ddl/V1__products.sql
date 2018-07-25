DROP TABLE IF EXISTS product;
CREATE TABLE product
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(200)            NOT NULL,
  description  VARCHAR(500),
  created_time TIMESTAMP DEFAULT now() NOT NULL,
  updated_time TIMESTAMP DEFAULT now() NOT NULL,
  created_by   VARCHAR(100)            NOT NULL,
  updated_by   VARCHAR(100)            NOT NULL
);

COMMENT ON COLUMN product.id IS '编号';
COMMENT ON COLUMN product.name IS '产品名称';
COMMENT ON COLUMN product.description IS '产品描述';
COMMENT ON COLUMN product.created_time IS '创建时间';
COMMENT ON COLUMN product.updated_time IS '更新时间';
COMMENT ON COLUMN product.created_by IS '创建人';
COMMENT ON COLUMN product.updated_by IS '更新人';


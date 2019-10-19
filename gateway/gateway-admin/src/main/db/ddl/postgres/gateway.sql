--网关路由表
DROP TABLE IF EXISTS gateway_route;
CREATE TABLE gateway_route
(
  id           VARCHAR(20) PRIMARY KEY,
  route_id     VARCHAR(100) NOT NULL,
  uri          VARCHAR(100) NOT NULL,
  predicates   TEXT         NOT NULL,
  filters      TEXT,
  orders       INT,
  description  VARCHAR(500),
  status       VARCHAR(1)            DEFAULT 'Y',
  created_time TIMESTAMP    NOT NULL DEFAULT now(),
  updated_time TIMESTAMP    NOT NULL DEFAULT now(),
  created_by   VARCHAR(100) NOT NULL,
  updated_by   VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ux_gateway_routes_uri ON gateway_route (uri);

COMMENT ON TABLE gateway_route IS '用户表';
COMMENT ON COLUMN gateway_route.id IS 'id';
COMMENT ON COLUMN gateway_route.route_id IS '路由id';
COMMENT ON COLUMN gateway_route.uri IS 'uri路径';
COMMENT ON COLUMN gateway_route.predicates IS '判定器';
COMMENT ON COLUMN gateway_route.filters IS '过滤器';
COMMENT ON COLUMN gateway_route.orders IS '排序';
COMMENT ON COLUMN gateway_route.status IS '状态：Y-有效，N-无效';
COMMENT ON COLUMN gateway_route.description IS '描述信息';
COMMENT ON COLUMN gateway_route.created_time IS '创建时间';
COMMENT ON COLUMN gateway_route.updated_time IS '更新时间';
COMMENT ON COLUMN gateway_route.created_by IS '创建人';
COMMENT ON COLUMN gateway_route.updated_by IS '更新人';

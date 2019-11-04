DROP DATABASE IF EXISTS sc_product;
CREATE DATABASE sc_product DEFAULT CHARSET utf8mb4;
USE sc_product;

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id           VARCHAR(20) PRIMARY KEY COMMENT '编号',
    name         VARCHAR(200)             NOT NULL COMMENT '产品名称',
    description  VARCHAR(500) COMMENT '产品描述',
    deleted      VARCHAR(1) DEFAULT 'N'   NOT NULL COMMENT '是否已删除Y：已删除，N：未删除',
    created_time datetime   DEFAULT now() NOT NULL COMMENT '创建时间',
    updated_time datetime   DEFAULT now() NOT NULL COMMENT '更新时间',
    created_by   VARCHAR(100)             NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100)             NOT NULL COMMENT '更新人'
) COMMENT '产品表';
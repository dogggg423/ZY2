-- ============================================================
-- 建表：产品表 / 供应商表 / 客户表 + 两张多对多中间表
--   客户  : 产品 = 1 : N   （外键 customer_id 放在产品表）
--   产品  : 供应商 = M : N （中间表 t_product_supplier）
--   供应商: 客户   = M : N （中间表 t_supplier_customer）
-- ============================================================

DROP TABLE IF EXISTS t_product_supplier;
DROP TABLE IF EXISTS t_supplier_customer;
DROP TABLE IF EXISTS t_product;
DROP TABLE IF EXISTS t_supplier;
DROP TABLE IF EXISTS t_customer;

CREATE TABLE t_customer (
    id   INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE t_supplier (
    id   INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE t_product (
    id          INT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    customer_id INT,                       -- 一对多：这件产品被哪个客户买走
    CONSTRAINT fk_product_customer FOREIGN KEY (customer_id) REFERENCES t_customer (id)
);

-- 产品-供应商 多对多中间表
CREATE TABLE t_product_supplier (
    product_id   INT NOT NULL,
    supplier_id  INT NOT NULL,
    PRIMARY KEY (product_id, supplier_id),
    CONSTRAINT fk_ps_product  FOREIGN KEY (product_id)  REFERENCES t_product (id),
    CONSTRAINT fk_ps_supplier FOREIGN KEY (supplier_id) REFERENCES t_supplier (id)
);

-- 供应商-客户 多对多中间表
CREATE TABLE t_supplier_customer (
    supplier_id INT NOT NULL,
    customer_id INT NOT NULL,
    PRIMARY KEY (supplier_id, customer_id),
    CONSTRAINT fk_sc_supplier FOREIGN KEY (supplier_id) REFERENCES t_supplier (id),
    CONSTRAINT fk_sc_customer FOREIGN KEY (customer_id) REFERENCES t_customer (id)
);

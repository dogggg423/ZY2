-- ============================================================
-- 测试数据
-- ============================================================

-- 客户：张三、李四、王五
INSERT INTO t_customer (id, name) VALUES (1, '张三');
INSERT INTO t_customer (id, name) VALUES (2, '李四');
INSERT INTO t_customer (id, name) VALUES (3, '王五');

-- 供应商：华强北电子、中关村科技、深圳智联
INSERT INTO t_supplier (id, name) VALUES (1, '华强北电子');
INSERT INTO t_supplier (id, name) VALUES (2, '中关村科技');
INSERT INTO t_supplier (id, name) VALUES (3, '深圳智联');

-- 产品：customer_id = 买走这件产品的客户（一对多的“多”端）
INSERT INTO t_product (id, name, price, customer_id) VALUES (1, '笔记本电脑', 5999.00, 1);
INSERT INTO t_product (id, name, price, customer_id) VALUES (2, '无线鼠标',   129.00, 1);
INSERT INTO t_product (id, name, price, customer_id) VALUES (3, '机械键盘',   399.00, 2);
INSERT INTO t_product (id, name, price, customer_id) VALUES (4, '4K显示器',  1699.00, 3);
INSERT INTO t_product (id, name, price, customer_id) VALUES (5, '激光打印机',1099.00, 2);

-- 产品-供应商（多对多）：哪件产品由哪些供应商供货
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (1, 1);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (1, 2);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (2, 2);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (3, 1);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (3, 3);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (4, 3);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (5, 1);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (5, 2);
INSERT INTO t_product_supplier (product_id, supplier_id) VALUES (5, 3);

-- 供应商-客户（多对多）：哪些客户买过哪个供应商的货
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (1, 1);
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (1, 2);
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (2, 1);
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (2, 3);
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (3, 2);
INSERT INTO t_supplier_customer (supplier_id, customer_id) VALUES (3, 3);

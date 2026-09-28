package org.example.mapper;

import org.example.entity.Product;

import java.util.List;

/**
 * 题目1：哪个产品被哪个客户买走了，哪个供应商提供 —— 用【XML】方式实现。
 * SQL 写在 src/main/resources/org/example/mapper/ProductMapper.xml 里。
 */
public interface ProductMapper {

    /**
     * 查询所有产品：每个产品带出「购买它的客户(1个)」和「供货的供应商(N个)」。
     * 对应 SQL 见 ProductMapper.xml 的 selectAllWithBuyerAndSuppliers。
     */
    List<Product> selectAllWithBuyerAndSuppliers();

    /**
     * 按产品 id 查询：该产品被哪个客户买走、由哪些供应商提供。
     */
    Product selectByIdWithBuyerAndSuppliers(Integer id);
}

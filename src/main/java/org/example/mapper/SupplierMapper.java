package org.example.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Customer;
import org.example.entity.Supplier;

import java.util.List;

/**
 * 题目2：查每个供应商卖了多少产品、分别被哪些客户买走了 —— 用【注解】方式实现。
 * SQL 直接写在 @Select 注解里，关联查询用 @Many 分步查询。
 */
public interface SupplierMapper {

    /**
     * 查询所有供应商：
     * - productCount：该供应商供应（卖出）的产品数量（子查询统计中间表 t_product_supplier）；
     * - customers：   购买过该供应商产品的客户列表（@Many 调用下面的分步查询）。
     */
    @Select("""
            SELECT s.id,
                   s.name,
                   (SELECT COUNT(*) FROM t_product_supplier ps
                     WHERE ps.supplier_id = s.id) AS product_count
              FROM t_supplier s
             ORDER BY s.id
            """)
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "productCount", column = "product_count"),
            @Result(property = "customers", column = "id",
                    many = @Many(select = "org.example.mapper.SupplierMapper.selectCustomersBySupplierId"))
    })
    List<Supplier> selectAllWithSales();

    /**
     * 分步查询：通过中间表 t_supplier_customer 查出购买某供应商产品的客户。
     */
    @Select("""
            SELECT c.id, c.name
              FROM t_customer c
              JOIN t_supplier_customer sc ON c.id = sc.customer_id
             WHERE sc.supplier_id = #{supplierId}
             ORDER BY c.id
            """)
    List<Customer> selectCustomersBySupplierId(Integer supplierId);
}

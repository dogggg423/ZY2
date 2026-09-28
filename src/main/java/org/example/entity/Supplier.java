package org.example.entity;

import java.util.List;

/** 供应商表 t_supplier 对应实体（题目2 用：供货数量 + 购买客户列表） */
public class Supplier {

    private Integer id;
    private String name;

    /** 该供应商供应（卖出）的产品数量 —— 题目2 查询结果 */
    private Integer productCount;

    /** 购买过该供应商产品的客户列表 —— 题目2 查询结果 */
    private List<Customer> customers;

    public Supplier() {
    }

    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Supplier{id=" + id + ", name='" + name + "', productCount=" + productCount
                + ", customers=" + customers + "}";
    }
}

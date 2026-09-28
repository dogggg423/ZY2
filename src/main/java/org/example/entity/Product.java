package org.example.entity;

import java.math.BigDecimal;
import java.util.List;

/** 产品表 t_product 对应实体（题目1 用：购买客户 + 供货供应商列表） */
public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;

    /** 购买该产品的客户（客户 : 产品 = 1 : N，外键在产品表上） */
    private Customer customer;

    /** 供应该产品的供应商列表（产品 : 供应商 = M : N，中间表 t_product_supplier） */
    private List<Supplier> suppliers;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price
                + ", customer=" + customer + ", suppliers=" + suppliers + "}";
    }
}

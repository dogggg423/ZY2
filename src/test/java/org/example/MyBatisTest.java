package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Supplier;
import org.example.mapper.ProductMapper;
import org.example.mapper.SupplierMapper;
import org.example.util.MyBatisUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * 两道题各写一个测试方法：
 *   题目1 -> test1_Xml_哪个产品被哪个客户买走_哪个供应商提供   （XML 方式）
 *   题目2 -> test2_Annotation_供应商卖了多少产品_被哪些客户买走（注解方式）
 *
 * 数据库：H2 内存库（mybatis-config.xml 配置），建表/插数据见 schema.sql、data.sql。
 */
class MyBatisTest {

    private static final String LINE = "+----+--------------+------------+----------+------------------------+";

    /**
     * ================== 题目1（XML 方式）==================
     * 哪个产品被哪个客户买走了，哪个供应商提供。
     */
    @Test
    void test1_Xml_哪个产品被哪个客户买走_哪个供应商提供() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);

            List<Product> products = productMapper.selectAllWithBuyerAndSuppliers();
            assertFalse(products.isEmpty(), "产品列表不应为空");

            System.out.println();
            System.out.println("=====================================================================");
            System.out.println("  题目1【XML 方式】：哪个产品被哪个客户买走了，哪个供应商提供");
            System.out.println("=====================================================================");
            System.out.println(LINE);
            System.out.printf("| %-2s | %-12s | %-10s | %-8s | %-22s |%n",
                    "ID", "产品", "价格", "购买客户", "供货供应商");
            System.out.println(LINE);
            for (Product p : products) {
                String buyer = (p.getCustomer() == null) ? "（未售出）" : p.getCustomer().getName();
                String supplierNames = p.getSuppliers().stream()
                        .map(Supplier::getName)
                        .collect(Collectors.joining("、"));
                System.out.printf("| %-2s | %-12s | %-10s | %-8s | %-22s |%n",
                        p.getId(), p.getName(),
                        String.format("￥%,.2f", p.getPrice()),
                        buyer, supplierNames);
            }
            System.out.println(LINE);

            // 断言抽查一件产品：3号“机械键盘”应被“李四”买走，由“华强北电子、深圳智联”供货
            Product keyboard = productMapper.selectByIdWithBuyerAndSuppliers(3);
            assertEquals("机械键盘", keyboard.getName());
            assertEquals("李四", keyboard.getCustomer().getName());
            assertEquals(2, keyboard.getSuppliers().size());
            System.out.println("抽查产品 id=3 -> " + keyboard);
            System.out.println();
        }
    }

    /**
     * ================== 题目2（注解方式）==================
     * 查供应商卖了多少产品，分别被哪些客户买走了。
     */
    @Test
    void test2_Annotation_供应商卖了多少产品_被哪些客户买走() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);

            List<Supplier> suppliers = supplierMapper.selectAllWithSales();
            assertFalse(suppliers.isEmpty(), "供应商列表不应为空");

            System.out.println();
            System.out.println("=====================================================================");
            System.out.println("  题目2【注解方式】：供应商卖了多少产品，分别被哪些客户买走了");
            System.out.println("=====================================================================");
            System.out.println("+----+--------------+--------------+------------------------+");
            System.out.printf("| %-2s | %-12s | %-10s | %-22s |%n",
                    "ID", "供应商", "卖出产品数", "购买客户");
            System.out.println("+----+--------------+--------------+------------------------+");
            for (Supplier s : suppliers) {
                String customerNames = s.getCustomers().stream()
                        .map(Customer::getName)
                        .collect(Collectors.joining("、"));
                System.out.printf("| %-2s | %-12s | %-12s | %-22s |%n",
                        s.getId(), s.getName(),
                        s.getProductCount() + " 件", customerNames);
            }
            System.out.println("+----+--------------+--------------+------------------------+");

            // 断言抽查：1号“华强北电子”供货 3 件产品，客户为“张三、李四”
            Supplier hqb = suppliers.get(0);
            assertEquals("华强北电子", hqb.getName());
            assertEquals(3, hqb.getProductCount());
            assertEquals(2, hqb.getCustomers().size());
            System.out.println("抽查供应商 id=1 -> " + hqb);
            System.out.println();
        }
    }
}

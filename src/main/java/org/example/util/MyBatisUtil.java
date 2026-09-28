package org.example.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.sql.Connection;

/**
 * MyBbatis 工具类：
 * 1. 读取 mybatis-config.xml 构建 SqlSessionFactory（H2 内存数据库）；
 * 2. 第一次初始化时执行 schema.sql / data.sql 建表并插入测试数据。
 */
public class MyBatisUtil {

    private static volatile SqlSessionFactory factory;

    public static synchronized SqlSessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                // 1. 读主配置文件
                Reader config = Resources.getResourceAsReader("mybatis-config.xml");
                factory = new SqlSessionFactoryBuilder().build(config);
                config.close();

                // 2. H2 内存库是空的，先建表 + 插数据
                try (SqlSession session = factory.openSession(true)) {
                    Connection conn = session.getConnection();
                    ScriptRunner runner = new ScriptRunner(conn);
                    runner.setLogWriter(null); // 不打印脚本执行日志
                    try (Reader schema = Resources.getResourceAsReader("schema.sql");
                         Reader data = Resources.getResourceAsReader("data.sql")) {
                        runner.runScript(schema);
                        runner.runScript(data);
                    }
                }
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        return factory;
    }

    public static SqlSession openSession() {
        return getSessionFactory().openSession(true);
    }
}

package com.ye;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void connect() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}

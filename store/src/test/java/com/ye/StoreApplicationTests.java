package com.ye;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
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

    @Test
    void test() throws IOException {

        InputStream is = new FileInputStream("D:\\ye\\document\\typora_lib\\1660096966866.png");
        MultipartFile file = new MockMultipartFile("1660096966866.png", is);
        System.out.println(file.getContentType());
    }

}

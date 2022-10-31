package com.ye.service;

import com.ye.entity.UserEntity;
import com.ye.server.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserEntityServiceTest {

    @Resource
    IUserService userService;

    @Test
    public void test01(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("po");
        userEntity.setPassword("123");
        userService.register(userEntity);
    }


}

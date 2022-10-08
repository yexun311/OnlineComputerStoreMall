package com.ye.mapper;

import com.ye.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void test01(){
        User user = new User();
        user.setUsername("ye");
        user.setPassword("111");
        user.setIsDelete(0);
        System.out.println(userMapper.insert(user));
        System.out.println(userMapper.selectUserByUsername("ye"));
    }


}

package com.ye.service;

import com.ye.entity.User;
import com.ye.ex.ServiceException;
import com.ye.server.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

    @Resource
    IUserService userService;

    @Test
    public void test01(){
        User user = new User();
        user.setUsername("po");
        user.setPassword("123");
        try{
            userService.register(user);
        }catch(ServiceException e){
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }


}

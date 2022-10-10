package com.ye.mapper;

import com.ye.entity.User;
import com.ye.server.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    IUserService userService;

    @Test
    public void test02(){
        /* User user = new User();
        user.setUsername("qqq");
        user.setPassword("123");
        userService.register(user); */
        //userService.changePassword(28,"qqq","123","111");
        //System.out.println(userService.login("qqq", "111"));

        /* User user = new User();
        user.setUid(26);
        user.setPhone("11010");
        user.setEmail("26@.com");
        user.setGender(1);
        if (userMapper.updateInfoByUid(user) == 1)
            System.out.println("success"); */
        System.err.println(userService.getByUid(26));
        User user = new User();
        user.setPhone("11111111");
        user.setEmail("11111@.com");
        user.setGender(0);
        userService.changeInfo(26, user);
        System.err.println(userService.getByUid(26));
    }

    @Test
    public void Test03(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setPhone("123123");
        user.setEmail("admin@123.com");
        user.setGender(1);
        user.setCreatedUser("admin");
        user.setCreatedTime(new Date());
        user.setModifiedUser("admin");
        user.setModifiedTime(new Date());

        userService.register(user);
    }

}

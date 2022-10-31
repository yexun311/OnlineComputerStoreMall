package com.ye.mapper;

import com.ye.entity.UserEntity;
import com.ye.server.IUserService;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class UserEntityMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void test01(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("ye");
        userEntity.setPassword("111");
        userEntity.setIsDelete(0);
        System.out.println(userMapper.insert(userEntity));
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
        UserEntity userEntity = new UserEntity();
        userEntity.setPhone("11111111");
        userEntity.setEmail("11111@.com");
        userEntity.setGender(0);
        userService.changeInfo(26, userEntity);
        System.err.println(userService.getByUid(26));
    }

    @Test
    public void Test03(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123456");
        userEntity.setPhone("123123");
        userEntity.setEmail("admin@123.com");
        userEntity.setGender(1);
        userEntity.setCreatedUser("admin");
        userEntity.setCreatedTime(new Date());
        userEntity.setModifiedUser("admin");
        userEntity.setModifiedTime(new Date());

        userService.register(userEntity);
    }

    @Test
    public void Test04(){
        JSONArray jsonArray = new JSONArray();
        UserEntity userEntity = new UserEntity();
        jsonArray.put(userEntity);
        System.out.println(jsonArray);
    }
}

package com.ye.server;

import com.ye.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
public interface IUserService {

    /** 注册 */
    void register(User user);

    /** 登录 */
    User login(String username, String password);
}

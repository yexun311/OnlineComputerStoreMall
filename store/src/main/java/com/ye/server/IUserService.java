package com.ye.server;

import com.ye.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口
 */
public interface IUserService {

    /** 注册 */
    void register(User user);

    /** 登录 */
    User login(String username, String password);

    /** 修改密码 */
    void changePassword(int uid, String username, String oldPassword, String newPassword);

    /**
     * 修改个人信息
     * uid 在控制层获取
     */
    void changeInfo(int uid, User user);

    /** 根据 uid 查询用户数据 */
    User getByUid(Integer uid);

    /** 修改头像 */
    void changeAvatar(int uid, String username, String avater);
}

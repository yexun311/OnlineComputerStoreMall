package com.ye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.model.req.RegisterReq;
import com.ye.model.resp.UserInfoResp;
import com.ye.model.dto.LoginDto;
import com.ye.model.entity.UserEntity;

/**
 * 用户业务层接口
 */
public interface IUserService extends IService<UserEntity> {

    /** 注册 */
    void register(RegisterReq RegisterReq);

    /** 登录 */
    LoginDto login(String account, String password);

    /** 修改密码 */
    void changePassword(int uid, String oldPassword, String newPassword);

    /**
     * 修改个人信息
     * uid 在控制层获取
     */
    void changeInfo(int uid, UserInfoResp userInfoResp);

    /** 根据 uid 查询用户数据 */
    UserInfoResp getByUid(Integer uid);

    /** 修改头像 */
    void changeAvatar(int uid, String avater);
}

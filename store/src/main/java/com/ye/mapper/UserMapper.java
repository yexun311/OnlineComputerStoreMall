package com.ye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ye.entity.UserEntity;

/**
 * 用户持久层
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /** 通过用户账号查找用户 */
    UserEntity selectUserByUsername(String username);

    /** 通过 uid 更新用户信息 */
    Integer updateInfoByUid(UserEntity userEntity);

}

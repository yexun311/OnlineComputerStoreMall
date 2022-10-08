package com.ye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ye.entity.User;


public interface UserMapper extends BaseMapper<User> {

    /** 通过用户账号查找用户 */
    User selectUserByUsername(String username);

}

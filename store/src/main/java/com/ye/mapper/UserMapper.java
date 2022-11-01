package com.ye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ye.model.entity.UserEntity;
import com.ye.model.resp.UserInfoResp;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久层
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /** 通过账号查找用户 */
    Integer selectUserByAccount(String account);

    /** 通过 uid 更新用户信息 */
    Integer updateInfoByUid(@Param("req") UserInfoResp userInfoResp);

}

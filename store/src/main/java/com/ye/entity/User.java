package com.ye.entity;

import lombok.Data;

/**
 * 用户类
 */
@Data
public class User extends BaseEntity{

    /** 用户id */
    private Integer uid;
    /** 用户账号 */
    private String username;
    /** 用户密码 */
    private String password;
    /** 盐值 */
    private String salt;
    /** 联系电话 */
    private String phone;
    /** 电子邮箱 */
    private String email;
    /** 性别：0-女，1-男 */
    private Integer gender;
    /** 头像 */
    private String avater;
    /** 是否删除：0-未删除，1-已删除 */
    private Integer is_delete;

}

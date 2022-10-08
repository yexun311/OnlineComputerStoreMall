package com.ye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class User extends BaseEntity{

    /** 用户id */
    @TableId(type = IdType.AUTO)
    private Integer uid;
    /** 用户账号 */
    @TableField("username")
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
    private Integer isDelete;

    public User() {
    }

    public User(Integer uid, String username, String password, String salt, String phone, String email, Integer gender, String avater, Integer isDelete) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.avater = avater;
        this.isDelete = isDelete;
    }
}

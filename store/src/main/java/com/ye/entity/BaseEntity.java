package com.ye.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基类
 */
@Data
public class BaseEntity {

    /** 创建者 */
    private String createdUser;
    /** 创建时间 */
    private Date createdTime;
    /** 修改者 */
    private String modifiedUser;
    /** 最后修改时间 */
    private Date modifiedTime;

}

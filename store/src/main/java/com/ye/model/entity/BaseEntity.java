package com.ye.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类
 */
@Data
public class BaseEntity implements Serializable {

    /** 创建时间 */
    private Date createTime;
    /** 最后修改时间 */
    private Date updateTime;

}

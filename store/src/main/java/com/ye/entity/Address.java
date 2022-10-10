package com.ye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货地址实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("address")
public class Address extends BaseEntity {

    /** 收货地址id */
	@TableId(type = IdType.AUTO)
	private Integer aid;
    /** 归属的用户id */
	private Integer uid;
	/** 收货人姓名 */
	private String name;
    /** 省-名称 */
	private String provinceName;
    /** 省-行政代号 */
	private String provinceCode;
    /** 市-名称 */
	private String cityName;
    /** 市-行政代号 */
	private String cityCode;
    /** 区-名称 */
	private String areaName;
    /** 区-行政代号 */
	private String areaCode;
    /** 邮政编码 */
	private String zip;
    /** 详细地址 */
	private String address;
    /** 联系电话 */
	private String phone;
    /** 固定电话 */
	private String tel;
    /** 标签 */
	private String tag;
    /** 默认地址 0-否，1-是 */
	private Integer isDefault;
}
package com.ye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.model.entity.AddressEntity;

/**
 * 收货地址业务层接口
 */
public interface IAddressService extends IService<AddressEntity> {

    /** 添加地址 */
    void addAddress(int uid, String username, AddressEntity address);
}

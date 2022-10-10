package com.ye.server;

import com.ye.entity.Address;

/**
 * 收货地址业务层接口
 */
public interface IAddressService {

    /** 添加地址 */
    void addAddress(int uid, String username, Address address);
}

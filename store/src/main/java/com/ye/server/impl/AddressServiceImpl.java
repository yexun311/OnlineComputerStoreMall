package com.ye.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ye.entity.Address;
import com.ye.entity.User;
import com.ye.ex.AddressOverflowException;
import com.ye.ex.InsertException;
import com.ye.ex.UserNotExistException;
import com.ye.mapper.AddressMapper;
import com.ye.mapper.UserMapper;
import com.ye.server.IAddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class AddressServiceImpl implements IAddressService {

    @Resource
    UserMapper userMapper;

    @Resource
    AddressMapper addressMapper;

    @Value("${address.maxCount}")
    Integer maxCount;

    /**
     * 收货地址新增
     * 限制收货地址为 20 条
     */
    @Override
    public void addAddress(int uid, String username, Address address) {
        // 判断用户是否存在
        User result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new UserNotExistException();

        // 判断地址数量是否溢出
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Address::getUid, uid);
        int count = addressMapper.selectCount(wrapper).intValue();
        if ( count == maxCount)
            throw new AddressOverflowException("地址已满（上限20条）");

        // 第一条地址设为默认
        address.setIsDefault(count == 0 ? 1 : 0);

        address.setUid(uid);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        if (addressMapper.insert(address) != 1)
            throw new InsertException("插入异常");
    }
}

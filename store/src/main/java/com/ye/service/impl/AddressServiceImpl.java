package com.ye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ye.model.entity.AddressEntity;
import com.ye.model.entity.UserEntity;
import com.ye.exception.FailException;
import com.ye.mapper.AddressMapper;
import com.ye.mapper.UserMapper;
import com.ye.service.IAddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements IAddressService {

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
    public void addAddress(int uid, String username, AddressEntity address) {
        // 判断用户是否存在
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException();

        // 判断地址数量是否溢出
        QueryWrapper<AddressEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AddressEntity::getUid, uid);
        int count = addressMapper.selectCount(wrapper).intValue();
        if ( count == maxCount)
            throw new FailException("地址已满（上限20条）");

        // 第一条地址设为默认
        address.setIsDefault(count == 0 ? 1 : 0);

        address.setUid(uid);
        address.setCreateTime(new Date());
        address.setUpdateTime(new Date());

        if (addressMapper.insert(address) != 1)
            throw new FailException("插入异常");
    }
}

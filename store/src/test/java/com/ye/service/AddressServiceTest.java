package com.ye.service;

import com.ye.model.entity.AddressEntity;
import com.ye.server.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AddressServiceTest {

    @Resource
    IAddressService addressService;

    @Test
    public void test01(){
        AddressEntity address = new AddressEntity();
        address.setCityName("cn");
        address.setIsDefault(1);
        addressService.addAddress(29, "admin", address);
    }
}

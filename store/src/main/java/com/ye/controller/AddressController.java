package com.ye.controller;

import com.ye.common.ResultData;
import com.ye.entity.Address;
import com.ye.server.IAddressService;
import com.ye.utils.SessionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Resource
    IAddressService addressService;

    /** 添加地址 */
    @RequestMapping("/add")
    public ResultData<Void> addAddress(Address address, HttpSession session){
        int uid = SessionUtils.getUidFromSession(session);
        String username = SessionUtils.getUsernameFromSession(session);
        addressService.addAddress(uid, username, address);

        return new ResultData<>(OK, "添加地址成功");
    }
}

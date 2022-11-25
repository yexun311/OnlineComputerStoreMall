package com.ye.controller;

import com.ye.common.result.Result;
import com.ye.common.result.ResultSet;
import com.ye.model.entity.AddressEntity;
import com.ye.service.IAddressService;
import com.ye.util.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    IAddressService addressService;

    /** 添加地址 */
    @RequestMapping("/add")
    public ResultSet<Void> addAddress(AddressEntity address, HttpSession session){
        int uid = SessionUtil.getUidFromSession(session);
        String username = SessionUtil.getUsernameFromSession(session);
        addressService.addAddress(uid, username, address);

        return Result.success("地址添加成功");
    }
}

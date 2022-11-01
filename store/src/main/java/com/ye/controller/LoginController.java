package com.ye.controller;

import com.ye.common.result.Result;
import com.ye.common.result.ResultSet;
import com.ye.model.dto.LoginDto;
import com.ye.model.req.LoginReq;
import com.ye.model.req.RegisterReq;
import com.ye.server.IUserService;
import com.ye.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录、注册、修改密码、登出
 */
@RestController
public class LoginController {

    @Resource
    IUserService userService;

    /** 注册 */
    @ApiOperation("注册")
    @RequestMapping("/reg")
    public ResultSet<Void> reg(@RequestBody RegisterReq registerReq){
        userService.register(registerReq);
        return Result.success("注册成功");
    }

    /** 登录
     * 获取 Session 对象中的 uid、username
     * avater 放在 cookie（在别处）
     */
    @ApiOperation("登录")
    @RequestMapping("/login")
    public ResultSet<LoginDto> login(@RequestBody LoginReq loginReq, HttpSession session){
        LoginDto loginDto = userService.login(loginReq.getAccount(), loginReq.getPassword());

        // 将 uid、username 放入 Session 便于使用
        session.setAttribute("uid", loginDto.getUid());
        session.setAttribute("username", loginDto.getUserName());

        return Result.success("登录成功", loginDto);
    }

    /**
     * 修改密码
     * 登录用户修改密码，获取旧密码和新密码即可，uid 和 username 从 session 中获取
     */
    @ApiOperation("修改密码")
    @RequestMapping("/change_password")
    public ResultSet<Void> changePassword(@RequestParam String oldPassword,
                                          String newPassword,
                                          HttpSession session){
        int uid = SessionUtil.getUidFromSession(session);
        userService.changePassword(uid, oldPassword, newPassword);

        return Result.success("修改成功");
    }

    public ResultSet<Void> logout(){

        return Result.success("登出成功");
    }

}

package com.ye.controller;

import com.mysql.cj.Session;
import com.ye.common.ResultData;
import com.ye.entity.User;
import com.ye.server.IUserService;
import com.ye.utils.SessionUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    IUserService userService;

    /** 注册 */
    @RequestMapping("/reg")
    public ResultData<Void> reg(User user){
        userService.register(user);
        return new ResultData<>(OK, "注册成功");
    }

    /** 登录
     * 获取 Session 对象中的 uid、username
     * avater 放在 cookie（在别处）
     */
    @RequestMapping("/login")
    public ResultData<User> reg(String username, String password, HttpSession session){
        User user = userService.login(username, password);

        // 将 uid、username 放入 Session 便于使用
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());

        return new ResultData<>(OK,"登录成功", user);
    }
}

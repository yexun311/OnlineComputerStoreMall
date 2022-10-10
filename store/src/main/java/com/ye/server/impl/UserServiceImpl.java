package com.ye.server.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ye.entity.User;
import com.ye.ex.*;
import com.ye.mapper.UserMapper;
import com.ye.server.IUserService;
import com.ye.utils.MD5Utils;
import com.ye.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    /** 注册 */
    @Override
    public void register(User user){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()))
            throw new NullException("用户名或密码为空");
        if (!Objects.isNull(userMapper.selectUserByUsername(user.getUsername())))
            throw new UsernameDuplicatedException("用户名被占用");

        /*
         * 密码加密处理作用:
         * 1.后端不再能直接看到用户的密码2.忽略了密码原来的强度,提升了数据安全性
         * 密码加密处理的实现:
         * 串+password+串->交给md5算法连续加密三次后转为大写
         * 串就是数据库字段中的盐值,是一个随机字符串
         */
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(getMD5Password(user.getPassword(), salt));
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(new Date());

        if (userMapper.insert(user) != 1)
            throw new InsertException("插入异常");

    }

    /** 登录 */
    @Override
    public User login(String username, String password){

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new NullException();
        }
        User result = userMapper.selectUserByUsername(username);
        if (Objects.isNull(result) || result.getIsDelete() == 1){
            throw new UserNotExistException();// 用户不存在
        }

        // 比较密码
        if (!result.getPassword().equals(getMD5Password(password, result.getSalt()))){
            throw new PasswordNotMachException();// 密码不匹配
        }

        //方法login返回的用户数据是为了辅助其他页面做数据展示使用(只会用到uid,username,avatar)
        //所以可以new一个新的user只赋这三个变量的值,这样使层与层之间传输时数据体量变小,后台层与
        // 层之间传输时数据量越小性能越高,前端也是的,数据量小了前端响应速度就变快了
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvater(result.getAvater());// 头像

        return result;

    }

    /** 修改密码 */
    @Override
    public void changePassword(int uid, String username, String oldPassword, String newPassword){
        // 验证用户是否存在
        User result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new UserNotExistException("用户不存在");
        // 验证原密码是否正确
        if (!result.getPassword().equals(getMD5Password(oldPassword, result.getSalt())))
            throw new PasswordNotMachException("密码错误");
        // 更新密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(User::getPassword, getMD5Password(newPassword, result.getSalt()))
                .set(User::getModifiedUser, username) // 更新人
                .set(User::getModifiedTime, new Date()) // 更新时间
                .eq(User::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new UpdateException("更新异常");
    }

    /** 修改个人资料是显示当前个人信息 */
    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new UserNotExistException("用户不存在");
        // 返回要用到的数据即可
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * 修改个人信息
     * 状态：已登录
     */
    @Override
    public void changeInfo(int uid, User user) {
        User result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new UserNotExistException("用户不存在");
        user.setUid(uid);
        user.setUsername(result.getUsername());
        user.setModifiedUser(result.getUsername());
        user.setModifiedTime(new Date());
        if (userMapper.updateInfoByUid(user) != 1)
            throw new UpdateException("更新异常");
    }

    /** 更换头像 */
    @Override
    public void changeAvatar(int uid, String avater, String username) {
        User result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new UserNotExistException("用户不存在");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(User::getAvater, avater)
                .set(User::getModifiedUser, username)
                .set(User::getModifiedTime, new Date())
                .eq(User::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new UpdateException("更新异常");
    }


    public String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = MD5Utils.MD5(salt + password + salt);
        }
        return password.toUpperCase();

    }
}

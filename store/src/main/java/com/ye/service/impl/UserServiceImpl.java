package com.ye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ye.model.req.RegisterReq;
import com.ye.model.resp.UserInfoResp;
import com.ye.model.dto.LoginDto;
import com.ye.model.entity.UserEntity;
import com.ye.exception.FailException;
import com.ye.mapper.UserMapper;
import com.ye.service.IUserService;
import com.ye.util.MD5Util;
import com.ye.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Resource
    UserMapper userMapper;

    /** 注册 */
    @Override
    public void register(RegisterReq registerReq){
        if (StringUtil.isEmpty(registerReq.getAccount()) || StringUtil.isEmpty(registerReq.getPassword()))
            throw new FailException("账号或密码为空");
        if (userMapper.selectUserByAccount(registerReq.getAccount()) >= 1)
            throw new FailException("账号已存在");
        if (userMapper.selectCount(new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUserName, registerReq.getUserName())
                .eq(UserEntity::getIsDelete, 0)) >= 1)
            throw new FailException("用户名已存在");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registerReq, userEntity);
        /*
         * 密码加密处理作用:
         * 1.后端不再能直接看到用户的密码2.忽略了密码原来的强度,提升了数据安全性
         * 密码加密处理的实现:
         * 串+password+串->交给md5算法连续加密三次后转为大写
         * 串就是数据库字段中的盐值,是一个随机字符串
         */
        String salt = UUID.randomUUID().toString();
        userEntity.setSalt(salt);
        userEntity.setPassword(getMD5Password(registerReq.getPassword(), salt));
        userEntity.setIsDelete(0);
        userEntity.setCreateTime(new Date());

        if (userMapper.insert(userEntity) != 1)
            throw new FailException("插入异常");
    }

    /** 登录 */
    @Override
    public LoginDto login(String account, String password){

        if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)){
            throw new FailException("账号或密码为空");
        }
        UserEntity result = userMapper.selectOne(new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getAccount, account)
                .eq(UserEntity::getIsDelete, 0));
        if (Objects.isNull(result)){
            throw new FailException("用户不存在");// 用户不存在
        }

        // 比较密码
        if (!result.getPassword().equals(getMD5Password(password, result.getSalt()))){
            throw new FailException("密码错误");// 密码不匹配
        }

        //方法login返回的用户数据是为了辅助其他页面做数据展示使用(只会用到uid,username,avatar)
        //所以可以new一个新的user只赋这三个变量的值,这样使层与层之间传输时数据体量变小,后台层与
        // 层之间传输时数据量越小性能越高,前端也是的,数据量小了前端响应速度就变快了
        LoginDto loginDto = new LoginDto();
        loginDto.setUid(result.getUid());
        loginDto.setUserName(result.getUserName());
        //loginDto.setAvatar(result.getAvatar());// 头像

        return loginDto;

    }

    /** 修改密码 */
    @Override
    public void changePassword(int uid, String oldPassword, String newPassword){
        // 验证用户是否存在
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        // 验证原密码是否正确
        if (!result.getPassword().equals(getMD5Password(oldPassword, result.getSalt())))
            throw new FailException("原密码错误");
        // 更新密码
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(UserEntity::getPassword, getMD5Password(newPassword, result.getSalt()))
                .eq(UserEntity::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new FailException("更新异常");
    }

    /** 显示用户信息 */
    @Override
    public UserInfoResp getByUid(Integer uid) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        // 返回要用到的数据即可
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setUserName(result.getUserName());
        userInfoResp.setPhone(result.getPhone());
        userInfoResp.setEmail(result.getEmail());
        userInfoResp.setGender(result.getGender());
        return userInfoResp;
    }

    /**
     * 修改个人信息
     */
    @Override
    public void changeInfo(int uid, UserInfoResp userInfoResp) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        userInfoResp.setUid(uid);
        if (userMapper.updateInfoByUid(userInfoResp) != 1)
            throw new FailException("更新异常");
    }

    /** 更换头像 */
    @Override
    public void changeAvatar(int uid, String avatar) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(UserEntity::getAvatar, avatar)
                .eq(UserEntity::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new FailException("更新异常");
    }


    public String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = MD5Util.MD5(salt + password + salt);
        }
        return password.toUpperCase();

    }
}

package com.ye.server.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ye.entity.UserEntity;
import com.ye.exception.FailException;
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
    public void register(UserEntity userEntity){
        if (StringUtils.isEmpty(userEntity.getUsername()) || StringUtils.isEmpty(userEntity.getPassword()))
            throw new FailException("用户名或密码为空");
        if (!Objects.isNull(userMapper.selectUserByUsername(userEntity.getUsername())))
            throw new FailException("用户名被占用");

        /*
         * 密码加密处理作用:
         * 1.后端不再能直接看到用户的密码2.忽略了密码原来的强度,提升了数据安全性
         * 密码加密处理的实现:
         * 串+password+串->交给md5算法连续加密三次后转为大写
         * 串就是数据库字段中的盐值,是一个随机字符串
         */
        String salt = UUID.randomUUID().toString();
        userEntity.setSalt(salt);
        userEntity.setPassword(getMD5Password(userEntity.getPassword(), salt));
        userEntity.setIsDelete(0);
        userEntity.setCreatedUser(userEntity.getUsername());
        userEntity.setCreatedTime(new Date());

        if (userMapper.insert(userEntity) != 1)
            throw new FailException("插入异常");

    }

    /** 登录 */
    @Override
    public UserEntity login(String username, String password){

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new FailException("账号或密码为空");
        }
        UserEntity result = userMapper.selectUserByUsername(username);
        if (Objects.isNull(result) || result.getIsDelete() == 1){
            throw new FailException();// 用户不存在
        }

        // 比较密码
        if (!result.getPassword().equals(getMD5Password(password, result.getSalt()))){
            throw new FailException();// 密码不匹配
        }

        //方法login返回的用户数据是为了辅助其他页面做数据展示使用(只会用到uid,username,avatar)
        //所以可以new一个新的user只赋这三个变量的值,这样使层与层之间传输时数据体量变小,后台层与
        // 层之间传输时数据量越小性能越高,前端也是的,数据量小了前端响应速度就变快了
        UserEntity userEntity = new UserEntity();
        userEntity.setUid(result.getUid());
        userEntity.setUsername(result.getUsername());
        userEntity.setAvatar(result.getAvatar());// 头像

        return result;

    }

    /** 修改密码 */
    @Override
    public void changePassword(int uid, String username, String oldPassword, String newPassword){
        // 验证用户是否存在
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        // 验证原密码是否正确
        if (!result.getPassword().equals(getMD5Password(oldPassword, result.getSalt())))
            throw new FailException("密码错误");
        // 更新密码
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(UserEntity::getPassword, getMD5Password(newPassword, result.getSalt()))
                .set(UserEntity::getModifiedUser, username) // 更新人
                .set(UserEntity::getModifiedTime, new Date()) // 更新时间
                .eq(UserEntity::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new FailException("更新异常");
    }

    /** 修改个人资料是显示当前个人信息 */
    @Override
    public UserEntity getByUid(Integer uid) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        // 返回要用到的数据即可
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(result.getUsername());
        userEntity.setPhone(result.getPhone());
        userEntity.setEmail(result.getEmail());
        userEntity.setGender(result.getGender());
        return userEntity;
    }

    /**
     * 修改个人信息
     * 状态：已登录
     */
    @Override
    public void changeInfo(int uid, UserEntity userEntity) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        userEntity.setUid(uid);
        userEntity.setUsername(result.getUsername());
        userEntity.setModifiedUser(result.getUsername());
        userEntity.setModifiedTime(new Date());
        if (userMapper.updateInfoByUid(userEntity) != 1)
            throw new FailException("更新异常");
    }

    /** 更换头像 */
    @Override
    public void changeAvatar(int uid, String avatar, String username) {
        UserEntity result = userMapper.selectById(uid);
        if (Objects.isNull(result) || result.getIsDelete() == 1)
            throw new FailException("用户不存在");
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(UserEntity::getAvatar, avatar)
                .set(UserEntity::getModifiedUser, username)
                .set(UserEntity::getModifiedTime, new Date())
                .eq(UserEntity::getUid, uid);
        if (userMapper.update(null, wrapper) != 1)
            throw new FailException("更新异常");
    }


    public String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = MD5Utils.MD5(salt + password + salt);
        }
        return password.toUpperCase();

    }
}

package com.xin.daily.service.user.impl;

import com.xin.daily.dao.UserLoginMapper;
import com.xin.daily.entity.UserLogin;
import com.xin.daily.service.user.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户登录service实现类
 *
 * @author creator mafh 2019/11/15 13:44
 * @author updater
 * @version 1.0.0
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {

    /**
     * 用户登录dao
     */
    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * 注册
     *
     * @param account  账户
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @param email    邮箱
     * @return id
     */
    @Override
    public int register(String account, String username, String password, String phone, String email) {

        /*----------------------------------- 日志记录 -----------------------------------*/


        /*----------------------------------- 参数校验 -----------------------------------*/
        // 当前时间


        /*----------------------------------- 业务处理 -----------------------------------*/
        // 1.判断是是否重复
        // 2.插入记录
        UserLogin userLogin = new UserLogin();
        userLogin.setAccount(account);
        userLogin.setUsername(username);
        userLogin.setUserCode("");
        userLogin.setEncryptPassword(password);
        userLogin.setSalt("");
        userLogin.setPhone(phone);
        userLogin.setEmail(email);
        userLogin.setPlatform("");
        userLogin.setCreateTime(new Date());
        userLogin.setCreator("");
        userLogin.setCreatorIp("");
        userLogin.setModifyTime(new Date());
        userLogin.setModifier("");
        userLogin.setModifierIp("");
        int id = userLoginMapper.insert(userLogin);

        /*----------------------------------- 日志记录 -----------------------------------*/


        /*----------------------------------- 方法返回 -----------------------------------*/
        return id;
    }
}

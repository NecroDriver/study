package com.xin.daily.service.user;

/**
 * 用户登录service
 *
 * @author creator mafh 2019/11/15 13:43
 * @author updater
 * @version 1.0.0
 */
public interface IUserLoginService {

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
    int register(String account, String username, String password, String phone, String email);
}

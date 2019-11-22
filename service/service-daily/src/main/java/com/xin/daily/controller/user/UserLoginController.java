package com.xin.daily.controller.user;

import com.xin.daily.service.user.IUserLoginService;
import com.xin.web.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录控制类
 *
 * @author creator mafh 2019/11/15 13:40
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/study/user")
public class UserLoginController {

    /**
     * 用户登录service
     */
    @Autowired
    private IUserLoginService userLoginService;

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
    @PostMapping(value = "/register")
    public ResultVo register(String account, String username, String password, String phone, String email) {
        int id = userLoginService.register(account, username, password, phone, email);
        return ResultVo.newResultVo(id > 0, id, "注册");
    }
}

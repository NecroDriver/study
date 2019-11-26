package com.xin.web.vo;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author creator mafh 2019/11/26 10:45
 * @author updater
 * @version 1.0.0
 */
public class UserVo implements Serializable {

    /**
     * 登录账户
     */
    private String account;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 平台
     */
    private String platform;
}

package com.xin.daily.service.user.impl;

import com.xin.daily.dao.UserLoginMapper;
import com.xin.daily.entity.UserLogin;
import com.xin.daily.service.user.IUserLoginService;
import com.xin.redis.util.RedisUtils;
import com.xin.web.base.BaseService;
import com.xin.web.consts.RedisConst;
import com.xin.web.pojo.Context;
import com.xin.web.utils.convert.ConvertUtils;
import com.xin.web.utils.crypt.SnowFlake;
import com.xin.web.vo.UserVo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 用户登录service实现类
 *
 * @author creator mafh 2019/11/15 13:44
 * @author updater
 * @version 1.0.0
 */
@Service
public class UserLoginServiceImpl extends BaseService implements IUserLoginService {

    /**
     * 用户登录dao
     */
    @Autowired
    private UserLoginMapper userLoginMapper;
    /**
     * redis工具类
     */
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 注册
     *
     * @param context  上下文
     * @param account  账户
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @param email    邮箱
     * @return num
     */
    @Override
    public int register(Context context, String account, String username, String password, String phone, String email) {

        /*----------------------------------- 日志记录 -----------------------------------*/
        logger.debug("注册，账户：{}，用户名：{}，密码：{}，手机号：{}，邮箱：{}", account, username, password, phone, email);

        /*----------------------------------- 参数校验 -----------------------------------*/
        Assert.notNull(account, "账户不能为空！");
        Assert.notNull(username, "用户名不能为空！");
        Assert.notNull(password, "密码不能为空！");
        Assert.notNull(phone, "手机号不能为空！");
        Assert.notNull(email, "邮箱不能为空！");
        // 当前时间
        Date nowDate = new Date();

        /*----------------------------------- 业务处理 -----------------------------------*/
        // 1.判断账号是否重复
        Assert.isNull(userLoginMapper.selectByAccount(account), "该账户已被注册！");
        // 2.插入记录
        UserLogin userLogin = new UserLogin();
        userLogin.setAccount(account);
        userLogin.setUsername(username);
        // 用雪花算法生成id
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String userCode = "YH" + snowFlake.nextId();
        userLogin.setUserCode(userCode);
        // 用bCrypt进行加密
        userLogin.setEncryptPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userLogin.setPhone(phone);
        userLogin.setEmail(email);
        userLogin.setPlatform("study");
        userLogin.setCreateTime(nowDate);
        userLogin.setCreator(username);
        userLogin.setCreatorIp("");
        userLogin.setModifyTime(nowDate);
        userLogin.setModifier(username);
        userLogin.setModifierIp("");
        int num = userLoginMapper.insert(userLogin);

        /*----------------------------------- 日志记录 -----------------------------------*/
        logger.debug("注册成功！记录id：{}", userLogin.getId());

        /*----------------------------------- 方法返回 -----------------------------------*/
        return num;
    }

    /**
     * 登录
     *
     * @param context  上下文
     * @param account  账户
     * @param password 密码
     * @return 结果
     */
    @Override
    public boolean login(Context context, String account, String password) {

        /*----------------------------------- 日志记录 -----------------------------------*/
        logger.debug("登录，账户：{}，密码：{}", account, password);

        /*----------------------------------- 参数校验 -----------------------------------*/
        Assert.notNull(account, "账户不能为空！");
        Assert.notNull(password, "密码不能为空！");

        /*----------------------------------- 业务处理 -----------------------------------*/
        // 查询账户
        UserLogin userLogin = userLoginMapper.selectByAccount(account);
        Assert.notNull(userLogin, "该账户不存在！");
        // 验证密码
        boolean result = BCrypt.checkpw(password, userLogin.getEncryptPassword());
        // 登录成功
        if (result) {
            // 生成token
            String token = UUID.randomUUID().toString();
            // 转化vo
            UserVo userVo = ConvertUtils.convert(userLogin, UserVo.class);
            // 存入redis，过期时间1小时
            redisUtils.set(RedisConst.USER_LOGIN_KEY + token, userVo, 3600);
        }

        /*----------------------------------- 日志记录 -----------------------------------*/
        logger.debug("登录结束！结果：{}", result);

        /*----------------------------------- 方法返回 -----------------------------------*/
        return result;
    }
}

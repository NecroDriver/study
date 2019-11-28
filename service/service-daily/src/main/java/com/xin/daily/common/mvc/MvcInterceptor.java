package com.xin.daily.common.mvc;

import com.xin.redis.util.RedisUtils;
import com.xin.web.consts.CookieConst;
import com.xin.web.consts.RedisConst;
import com.xin.web.interceptor.InitHandlerInterceptorAdapter;
import com.xin.web.pojo.Context;
import com.xin.web.utils.convert.JsonUtils;
import com.xin.web.utils.cookie.CookieUtils;
import com.xin.web.vo.ResultVo;
import com.xin.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVC拦截器
 *
 * @author creator mafh 2019/11/27 16:55
 * @author updater
 * @version 1.0.0
 */
@Component
public class MvcInterceptor extends InitHandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(MvcInterceptor.class);

    /**
     * redis工具类
     */
    private RedisUtils redisUtils;

    public MvcInterceptor(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从cookie获取token
        String token = CookieUtils.getCookieValue(request, CookieConst.USER_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            // 从redis中获取用户信息
            Object userJson = redisUtils.get(RedisConst.USER_LOGIN_KEY + token);
            if (!ObjectUtils.isEmpty(userJson)) {
                // 登录信息赋值
                UserVo userVo = JsonUtils.fromJson(userJson.toString(), UserVo.class);
                Context context = new Context();
                context.setUser(userVo);
                request.setAttribute("context", context);
                return true;
            }
        }
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("loginUrl", "");
            ResultVo result = ResultVo.failureVo("未授权，被拦截", data);
            out.print(JsonUtils.toJson(result));
            out.flush();
            logger.info("{}请求未授权，被拦截", request.getRequestURL().toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    /**
     * 排除的规则
     */
    @Override
    public List<String> getExcludePathPatterns() {
        List<String> list = super.getExcludePathPatterns();
        list.add("/**/public/**");
        return list;
    }
}

package com.xin.web.pojo;

import com.xin.web.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上下文
 *
 * @author creator mafh 2019/11/26 10:47
 * @author updater
 * @version 1.0.0
 */
public class Context {

    /**
     * 用户信息
     */
    private UserVo user;
    /**
     * 请求信息
     */
    private HttpServletRequest request;
    /**
     * 响应信息
     */
    private HttpServletResponse response;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}

package com.jxw.design.model;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 15:33
 * @Description
 */
public class WeChatLogin implements Serializable {
    private static final long serialVersionUID = 9189173006444252266L;
    /**
     * 微信授权登录时的openID
     */
    private String openId;
    /**
     * 微信授权登录时的token
     */
    private String token;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.jxw.design.enums;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/23 16:11
 * 微信登录信息
 */
public enum AppInfo {
    /**
     * 微信公众号唯一id
     */
    APPID("wx67c9c18706040be8"),
    /**
     * 微信公众号secret
     */
    APPSECRET("86a976332c05f555ecb7f1e8e79acf8d");
    private String appInfo;

    AppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }
}

package com.jxw.design.model.req;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/29 11:35
 * <p>
 * 用户排行榜请求实体
 */
public class UserRankReq {
    /**
     * 用户id（微信端openid）
     */
    private String userId;
    /**
     * 用户所在城市
     */
    private String userCity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }
}
